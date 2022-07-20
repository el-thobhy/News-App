package com.elthobhy.newsapp.ui.explore.detik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.Article
import com.elthobhy.newsapp.databinding.ActivityDetikBinding
import com.elthobhy.newsapp.utils.loadingExtension
import com.elthobhy.newsapp.viewmodel.DetikViewModel
import com.elthobhy.newsapp.viewmodel.ViewModelFactory

class DetikActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetikBinding
    private lateinit var detikAdapter: DetikAdapter
    private lateinit var detikViewModel: DetikViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetikBinding.inflate(layoutInflater)
        val factory = ViewModelFactory.getInstance()
        detikViewModel = ViewModelProvider(this, factory)[DetikViewModel::class.java]
        detikAdapter = DetikAdapter()
        setContentView(binding.root)
        initActionBar()
        showRvDetik()
    }

    private fun showRvDetik() {
        detikAdapter.notifyDataSetChanged()
        true.loadingExtension(binding.shimmerDetik,binding.rvDetik)
        binding.rvDetik.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
            adapter = detikAdapter
        }
        detikViewModel.getDetikNews().observe(this){listArticle->
            if(listArticle.isNotEmpty()){
                false.loadingExtension(binding.shimmerDetik,binding.rvDetik)
                detikAdapter.setList(listArticle)
            }else{
                true.loadingExtension(binding.shimmerDetik,binding.rvDetik)
            }
            Log.e("debug", "showRvDetik: $listArticle", )
        }
        detikAdapter.setOnItemClickCallback(object :DetikAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Article) {
                Toast.makeText(this@DetikActivity,"clicked",Toast.LENGTH_SHORT).show()
            }

        })
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