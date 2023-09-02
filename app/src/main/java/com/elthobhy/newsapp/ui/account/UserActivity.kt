package com.elthobhy.newsapp.ui.account

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.elthobhy.core.utils.dialogError
import com.elthobhy.core.utils.dialogLoading
import com.elthobhy.core.utils.vo.Status
import com.elthobhy.newsapp.databinding.ActivityUserBinding
import com.elthobhy.newsapp.ui.auth.changepassword.ChangePasswordActivity
import com.elthobhy.newsapp.ui.auth.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.koin.android.ext.android.inject

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private val userViewModel by inject<UserViewModel>()
    private var firebaseUser: FirebaseUser? = null
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseUser = FirebaseAuth.getInstance().currentUser
        dialog = dialogLoading(this)
        getDataUser()
        onClick()
    }

    private fun getDataUser() {
        binding.apply {
            val uid = firebaseUser?.uid
            if (uid != null) {
                userViewModel.getDataUser(uid).observe(this@UserActivity) {
                    when (it.status) {
                        Status.LOADING -> {
                            dialog.show()
                        }
                        Status.SUCCESS -> {
                            dialog.dismiss()
                            tvNameUser.text = it.data?.nameUser
                            Glide.with(this@UserActivity)
                                .load(it.data?.avatarUser)
                                .placeholder(android.R.color.darker_gray)
                                .into(ivUser)
                            tvEmailUser.text = it.data?.emailUser
                        }
                        Status.ERROR -> {
                            dialog.dismiss()
                            dialogError(it.message,this@UserActivity).show()
                        }
                    }
                }
            }
        }
    }

    private fun onClick() {
        binding.apply {
            btnClose.setOnClickListener {
                finish()
            }
            buttonChangePassword.setOnClickListener {
                startActivity(Intent(this@UserActivity, ChangePasswordActivity::class.java))
            }
            btnLogoutUser.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this@UserActivity, LoginActivity::class.java))
                finishAffinity()
            }
        }
    }
}