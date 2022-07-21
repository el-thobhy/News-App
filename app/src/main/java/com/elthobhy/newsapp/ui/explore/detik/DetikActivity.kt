package com.elthobhy.newsapp.ui.explore.detik

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.ArticleDetik
import com.elthobhy.newsapp.data.source.local.entity.ArticleHeadline
import com.elthobhy.newsapp.databinding.ActivityDetikBinding
import com.elthobhy.newsapp.ui.detail.DetailActivity
import com.elthobhy.newsapp.utils.Constants
import com.elthobhy.newsapp.utils.loadingExtension
import com.elthobhy.newsapp.utils.vo.Status
import com.elthobhy.newsapp.viewmodel.DetikViewModel
import com.elthobhy.newsapp.viewmodel.ViewModelFactory

class DetikActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetikBinding
    private lateinit var detikAdapter: DetikAdapter
    private lateinit var detikViewModel: DetikViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetikBinding.inflate(layoutInflater)
        val factory = ViewModelFactory.getInstance(this)
        detikViewModel = ViewModelProvider(this, factory)[DetikViewModel::class.java]
        detikAdapter = DetikAdapter()
        setContentView(binding.root)
        initActionBar()
        showRvDetik()
    }

    private fun showRvDetik() {
        detikAdapter.notifyDataSetChanged()
        binding.apply {
            true.loadingExtension(shimmerDetik,rvDetik)
            rvDetik.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                setHasFixedSize(true)
                adapter = detikAdapter
            }
            detikViewModel.getDetikNews().observe(this@DetikActivity){listArticle->
                if (listArticle != null) {
                    when(listArticle.status){
                        Status.LOADING -> true.loadingExtension(shimmerDetik,rvDetik)
                        Status.SUCCESS->{
                            listArticle.data?.let { detikAdapter.setList(it) }
                            detikAdapter.notifyDataSetChanged()
                            false.loadingExtension(shimmerDetik,rvDetik)
                        }
                        Status.ERROR->{
                            false.loadingExtension(shimmerDetik,rvDetik)
                        }
                    }
                }
                false.loadingExtension(shimmerDetik, rvDetik)
            }
        }

        detikAdapter.setOnItemClickCallback(object :DetikAdapter.OnItemClickCallback{
            override fun onItemClicked(data: ArticleDetik) {
                showDetailData(data)
            }

        })
    }

    private fun showDetailData(data: ArticleDetik) {
        val intentDetail = Intent(applicationContext, DetailActivity::class.java)
        intentDetail.putExtra(Constants.DETIK,data)
        startActivity(intentDetail)
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