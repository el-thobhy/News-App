package com.elthobhy.newsapp.ui.explore.suara

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
import org.koin.android.ext.android.inject

class SuaraActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuaraBinding
    private val suaraViewModel by inject<SuaraViewModel>()
    private val suaraAdapter by inject<SuaraAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuaraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showRvSuara()
        initActionBar()
    }

    private fun showRvSuara() {
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
                            imageErrorSuara.visibility = View.GONE
                            false.loadingExtension(shimmerSuara,rvSuara)
                        }
                        Status.ERROR->{
                            imageErrorSuara.visibility = View.VISIBLE
                            false.loadingExtension(shimmerSuara,rvSuara)
                        }
                    }
                }else{
                    imageErrorSuara.visibility = View.VISIBLE
                    false.loadingExtension(shimmerSuara, rvSuara)
                }
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