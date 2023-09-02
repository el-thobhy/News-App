package com.elthobhy.newsapp.ui.auth.forgotpassword

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.elthobhy.core.utils.dialogError
import com.elthobhy.core.utils.dialogLoading
import com.elthobhy.core.utils.vo.Status
import com.elthobhy.newsapp.databinding.ActivityForgotPasswordBinding
import com.elthobhy.newsapp.ui.auth.login.LoginActivity
import org.koin.android.ext.android.inject

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding
    private val forgotPasswordViewModel by inject<ForgotPasswordViewModel>()
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialog= dialogLoading(this)
        onClick()
    }

    private fun onClick() {
        binding.apply {
            btnForgotPassword.setOnClickListener {
                val email = etEmailForgotPassword.text.toString().trim()
                if(checkValidation(email)){
                    forgotPassword(email)
                }
            }
            btnCloseForgotPassword.setOnClickListener {
                finish()
            }
        }
    }

    private fun forgotPassword(email: String) {
        forgotPasswordViewModel.forgotPassword(email).observe(this){
            when (it.status) {
                Status.LOADING -> { dialog.show() }
                Status.SUCCESS -> {
                    dialog.dismiss()
                    Toast.makeText(
                        this@ForgotPasswordActivity,
                        "Reset Password Link has been sent, please check your inbox",
                        Toast.LENGTH_LONG
                    ).show()
                    startActivity(
                        Intent(
                            this@ForgotPasswordActivity,
                            LoginActivity::class.java
                        )
                    )
                    finishAffinity()
                }
                Status.ERROR -> {
                    dialog.dismiss()
                    dialogError(it.message,this).show()
                    Toast.makeText(
                        this@ForgotPasswordActivity,
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun checkValidation(email: String): Boolean {
        binding.apply {
            when{
                email.isEmpty()->{
                    etEmailForgotPassword.error = "Please Field Your Email"
                    etEmailForgotPassword.requestFocus()
                }
                !Patterns.EMAIL_ADDRESS.matcher(email).matches()->{
                    etEmailForgotPassword.error = "Pleas Field Correct Email address"
                    etEmailForgotPassword.requestFocus()
                }
                else->{
                    return true
                }
            }
        }
        return false
    }
}