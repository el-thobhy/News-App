package com.elthobhy.newsapp.ui.explore.tvone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.databinding.ActivityTvoneBinding

class TvoneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvoneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initActionBar()
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbTvone)
        binding.tbTvone.setNavigationOnClickListener {
            finish()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.tvone)
    }
}