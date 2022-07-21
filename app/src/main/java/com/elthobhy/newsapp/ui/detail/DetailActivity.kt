package com.elthobhy.newsapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbDetail)
        binding.tbDetail.setNavigationOnClickListener {
            finish()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""    }
}