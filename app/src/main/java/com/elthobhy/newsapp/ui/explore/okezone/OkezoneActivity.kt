package com.elthobhy.newsapp.ui.explore.okezone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.databinding.ActivityOkezoneBinding

class OkezoneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOkezoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOkezoneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initActionBar()
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbOkezone)
        binding.tbOkezone.setNavigationOnClickListener {
            finish()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.okezone)
    }
}