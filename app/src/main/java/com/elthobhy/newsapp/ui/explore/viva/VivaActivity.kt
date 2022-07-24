package com.elthobhy.newsapp.ui.explore.viva

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.viva.ArticleViva
import com.elthobhy.newsapp.databinding.ActivityVivaBinding
import com.elthobhy.newsapp.ui.detail.DetailActivity
import com.elthobhy.newsapp.utils.Constants
import com.elthobhy.newsapp.utils.loadingExtension
import com.elthobhy.newsapp.utils.vo.Status
import com.elthobhy.newsapp.viewmodel.viva.VivaViewModel
import org.koin.android.ext.android.inject

class VivaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVivaBinding
    private val vivaViewModel by inject<VivaViewModel>()
    private val vivaAdapter by inject<VivaAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVivaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showRvViva()
        initActionBar()
    }

    private fun showRvViva() {
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
                            imageErrorViva.visibility = View.GONE
                            false.loadingExtension(shimmerViva,rvViva)
                        }
                        Status.ERROR->{
                            imageErrorViva.visibility = View.VISIBLE
                            false.loadingExtension(shimmerViva,rvViva)
                        }
                    }
                }else{
                    imageErrorViva.visibility = View.VISIBLE
                    false.loadingExtension(shimmerViva, rvViva)
                }
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