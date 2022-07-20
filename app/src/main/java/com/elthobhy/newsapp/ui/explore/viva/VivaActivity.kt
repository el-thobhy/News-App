package com.elthobhy.newsapp.ui.explore.viva

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.databinding.ActivityVivaBinding

class VivaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVivaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVivaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initActionBar()
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbViva)
        binding.tbViva.setNavigationOnClickListener {
            finish()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.viva)
    }
}