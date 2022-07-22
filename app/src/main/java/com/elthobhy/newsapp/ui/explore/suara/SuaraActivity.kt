package com.elthobhy.newsapp.ui.explore.suara

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.suara.ArticleSuara
import com.elthobhy.newsapp.databinding.ActivitySuaraBinding
import com.elthobhy.newsapp.ui.detail.DetailActivity
import com.elthobhy.newsapp.utils.Constants
import com.elthobhy.newsapp.utils.loadingExtension
import com.elthobhy.newsapp.utils.vo.Status
import com.elthobhy.newsapp.viewmodel.suara.SuaraViewModel
import com.elthobhy.newsapp.viewmodel.ViewModelFactory

class SuaraActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuaraBinding
    private lateinit var suaraViewModel: SuaraViewModel
    private lateinit var suaraAdapter: SuaraAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuaraBinding.inflate(layoutInflater)
        val factory = ViewModelFactory.getInstance(this)
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
                if (listArticle != null) {
                    when(listArticle.status){
                        Status.LOADING -> true.loadingExtension(shimmerSuara,rvSuara)
                        Status.SUCCESS->{
                            listArticle.data?.let { suaraAdapter.setList(it) }
                            suaraAdapter.notifyDataSetChanged()
                            false.loadingExtension(shimmerSuara,rvSuara)
                        }
                        Status.ERROR->{
                            false.loadingExtension(shimmerSuara,rvSuara)
                        }
                    }
                }
                Log.e("debug", "showRvHeadline: $listArticle",)
                false.loadingExtension(shimmerSuara, rvSuara)
            }
        }
        suaraAdapter.setOnItemClickCallback(object : SuaraAdapter.OnItemClickCallback{
            override fun onItemClicked(data: ArticleSuara) {
                showDetailData(data)
            }

        })
    }

    private fun showDetailData(data: ArticleSuara) {
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