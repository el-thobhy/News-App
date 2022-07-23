package com.elthobhy.newsapp.ui.explore.kapanlagi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.kapanlagi.ArticleKapanlagi
import com.elthobhy.newsapp.databinding.ActivityKapanLagiBinding
import com.elthobhy.newsapp.ui.detail.DetailActivity
import com.elthobhy.newsapp.utils.Constants
import com.elthobhy.newsapp.utils.loadingExtension
import com.elthobhy.newsapp.utils.vo.Status
import com.elthobhy.newsapp.viewmodel.kapanlagi.KapanlagiViewModel
import org.koin.android.ext.android.inject

class KapanLagiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKapanLagiBinding
    private val kapanlagiViewModel by inject<KapanlagiViewModel>()
    private val kapanlagiAdapter by inject<KapanlagiAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKapanLagiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showRvKapanlagi()
        initActionBar()
    }

    private fun showRvKapanlagi() {
        binding.apply {
            true.loadingExtension(shimmerKapanlagi,rvKapanlagi)
            rvKapanlagi.apply {
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                setHasFixedSize(true)
                adapter = kapanlagiAdapter
            }
            kapanlagiViewModel.getKapanlagiNews().observe(this@KapanLagiActivity){listArticle->
                if (listArticle != null) {
                    when(listArticle.status){
                        Status.LOADING -> true.loadingExtension(shimmerKapanlagi,rvKapanlagi)
                        Status.SUCCESS->{
                            listArticle.data?.let { kapanlagiAdapter.setList(it) }
                            false.loadingExtension(shimmerKapanlagi,rvKapanlagi)
                        }
                        Status.ERROR->{
                            false.loadingExtension(shimmerKapanlagi,rvKapanlagi)
                        }
                    }
                }
                Log.e("debug", "showRvHeadline: $listArticle",)
                false.loadingExtension(shimmerKapanlagi, rvKapanlagi)
            }
        }
        kapanlagiAdapter.setOnItemClickCallback(object :KapanlagiAdapter.OnItemClickCallback{
            override fun onItemClicked(data: ArticleKapanlagi) {
                showDetailData(data)
            }

        })
    }

    private fun showDetailData(data: ArticleKapanlagi) {
        val intentDetail = Intent(applicationContext, DetailActivity::class.java)
        intentDetail.putExtra(Constants.KAPAN_LAGI,data)
        startActivity(intentDetail)
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbKapanlagi)
        binding.tbKapanlagi.setNavigationOnClickListener {
            finish()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.kapanlagi)
    }
}