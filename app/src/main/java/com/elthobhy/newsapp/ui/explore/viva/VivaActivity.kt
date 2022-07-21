package com.elthobhy.newsapp.ui.explore.viva

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.ArticleHeadline
import com.elthobhy.newsapp.data.source.local.entity.ArticleViva
import com.elthobhy.newsapp.databinding.ActivityVivaBinding
import com.elthobhy.newsapp.ui.detail.DetailActivity
import com.elthobhy.newsapp.utils.Constants
import com.elthobhy.newsapp.utils.loadingExtension
import com.elthobhy.newsapp.utils.vo.Status
import com.elthobhy.newsapp.viewmodel.ViewModelFactory
import com.elthobhy.newsapp.viewmodel.VivaViewModel

class VivaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVivaBinding
    private lateinit var vivaViewModel: VivaViewModel
    private lateinit var vivaAdapter: VivaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVivaBinding.inflate(layoutInflater)
        val factory = ViewModelFactory.getInstance(this)
        vivaViewModel = ViewModelProvider(this, factory)[VivaViewModel::class.java]
        vivaAdapter = VivaAdapter()
        setContentView(binding.root)
        showRvViva()
        initActionBar()
    }

    private fun showRvViva() {
        vivaAdapter.notifyDataSetChanged()
        binding.apply {
            true.loadingExtension(shimmerViva, rvViva)
            rvViva.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
                adapter = vivaAdapter
            }
            vivaViewModel.getVivaNews().observe(this@VivaActivity) { listArticle ->
                if (listArticle != null) {
                    when(listArticle.status){
                        Status.LOADING -> true.loadingExtension(shimmerViva,rvViva)
                        Status.SUCCESS->{
                            listArticle.data?.let { vivaAdapter.setList(it) }
                            vivaAdapter.notifyDataSetChanged()
                            false.loadingExtension(shimmerViva,rvViva)
                        }
                        Status.ERROR->{
                            false.loadingExtension(shimmerViva,rvViva)
                        }
                    }
                }
                Log.e("debug", "showRvHeadline: $listArticle",)
                false.loadingExtension(shimmerViva, rvViva)
            }
        }
        vivaAdapter.setOnItemClickCallback(object : VivaAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ArticleViva) {
                showDetailData(data)
            }

        })
    }

    private fun showDetailData(data: ArticleViva) {
        val intentDetail = Intent(applicationContext, DetailActivity::class.java)
        intentDetail.putExtra(Constants.VIVA, data)
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