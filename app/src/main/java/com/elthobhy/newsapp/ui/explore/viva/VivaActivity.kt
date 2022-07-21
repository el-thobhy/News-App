package com.elthobhy.newsapp.ui.explore.viva

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.Article
import com.elthobhy.newsapp.databinding.ActivityVivaBinding
import com.elthobhy.newsapp.ui.detail.DetailActivity
import com.elthobhy.newsapp.ui.explore.detik.DetikAdapter
import com.elthobhy.newsapp.utils.Constants
import com.elthobhy.newsapp.utils.loadingExtension
import com.elthobhy.newsapp.viewmodel.ViewModelFactory
import com.elthobhy.newsapp.viewmodel.VivaViewModel

class VivaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVivaBinding
    private lateinit var vivaViewModel: VivaViewModel
    private lateinit var vivaAdapter: VivaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVivaBinding.inflate(layoutInflater)
        val factory = ViewModelFactory.getInstance()
        vivaViewModel = ViewModelProvider(this,factory)[VivaViewModel::class.java]
        vivaAdapter = VivaAdapter()
        setContentView(binding.root)
        showRvViva()
        initActionBar()
    }

    private fun showRvViva() {
        vivaAdapter.notifyDataSetChanged()
        true.loadingExtension(binding.shimmerViva,binding.rvViva)
        binding.rvViva.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
            adapter = vivaAdapter
        }
        vivaViewModel.getVivaNews().observe(this){listArticle->
            if(listArticle.isNotEmpty()){
                false.loadingExtension(binding.shimmerViva,binding.rvViva)
                vivaAdapter.setList(listArticle)
            }else{
                true.loadingExtension(binding.shimmerViva,binding.rvViva)
            }
            Log.e("debug", "showRvViva: $listArticle", )
        }
        vivaAdapter.setOnItemClickCallback(object : VivaAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Article) {
                showDetailData(data)
            }

        })
    }

    private fun showDetailData(data: Article) {
        val intentDetail = Intent(applicationContext, DetailActivity::class.java)
        intentDetail.putExtra(Constants.VIVA,data)
        startActivity(intentDetail)
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