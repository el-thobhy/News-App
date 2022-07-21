package com.elthobhy.newsapp.ui.explore.suara

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.Article
import com.elthobhy.newsapp.databinding.ActivitySuaraBinding
import com.elthobhy.newsapp.ui.detail.DetailActivity
import com.elthobhy.newsapp.ui.explore.kapanlagi.KapanlagiAdapter
import com.elthobhy.newsapp.utils.Constants
import com.elthobhy.newsapp.utils.loadingExtension
import com.elthobhy.newsapp.viewmodel.SuaraViewModel
import com.elthobhy.newsapp.viewmodel.ViewModelFactory

class SuaraActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuaraBinding
    private lateinit var suaraViewModel: SuaraViewModel
    private lateinit var suaraAdapter: SuaraAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuaraBinding.inflate(layoutInflater)
        val factory = ViewModelFactory.getInstance()
        suaraViewModel = ViewModelProvider(this,factory)[SuaraViewModel::class.java]
        suaraAdapter = SuaraAdapter()
        setContentView(binding.root)
        showRvSuara()
        initActionBar()
    }

    private fun showRvSuara() {
        suaraAdapter.notifyDataSetChanged()
        binding.apply {
            true.loadingExtension(shimmerSuara,rvSuara)
            rvSuara.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                setHasFixedSize(true)
                adapter = suaraAdapter
            }
            suaraViewModel.getSuaraNews().observe(this@SuaraActivity){listArticle->
                if(listArticle.isNotEmpty()){
                    false.loadingExtension(shimmerSuara,rvSuara)
                    suaraAdapter.setList(listArticle)
                }else{
                    true.loadingExtension(shimmerSuara,rvSuara)

                }
                Log.e("debug", "showRvKapanlagi: $listArticle", )
            }
        }
        suaraAdapter.setOnItemClickCallback(object : SuaraAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Article) {
                showDetailData(data)
            }

        })
    }

    private fun showDetailData(data: Article) {
        val intentDetail = Intent(applicationContext, DetailActivity::class.java)
        intentDetail.putExtra(Constants.SUARA,data)
        startActivity(intentDetail)
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbSuara)
        binding.tbSuara.setNavigationOnClickListener {
            finish()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.suara)
    }
}