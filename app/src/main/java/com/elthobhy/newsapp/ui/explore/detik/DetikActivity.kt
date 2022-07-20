package com.elthobhy.newsapp.ui.explore.detik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.databinding.ActivityDetikBinding

class DetikActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetikBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetikBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initActionBar()
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbDetik)
        binding.tbDetik.setNavigationOnClickListener {
            finish()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.detik)
    }
}