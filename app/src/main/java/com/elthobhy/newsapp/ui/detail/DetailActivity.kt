package com.elthobhy.newsapp.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.Article
import com.elthobhy.newsapp.databinding.ActivityDetailBinding
import com.elthobhy.newsapp.utils.Constants
import com.elthobhy.newsapp.viewmodel.HeadlineViewModel
import com.elthobhy.newsapp.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listKey = arrayListOf(
            Constants.BUSINESS,
            Constants.ENTERTAINMENT,
            Constants.GENERAL,
            Constants.HEALTH,
            Constants.SCIENCE,
            Constants.SPORTS,
            Constants.TECHNOLOGY,
            Constants.TOP_HEADLINE,
            Constants.DETIK,
            Constants.VIVA,
            Constants.KAPAN_LAGI,
            Constants.SUARA
        )
        for(key in listKey){
            showDetail(key)
        }
    }

    private fun onClick(url: String?) {
        binding.goToLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    private fun showDetail(key: String) {
        when (key){
            Constants.BUSINESS->{
                val data = intent?.getParcelableExtra<Article>(Constants.BUSINESS)
                displayDetail(data)
            }
            Constants.ENTERTAINMENT->{
                val data = intent?.getParcelableExtra<Article>(Constants.ENTERTAINMENT)
                displayDetail(data)
            }
            Constants.GENERAL->{
                val data = intent?.getParcelableExtra<Article>(Constants.GENERAL)
                displayDetail(data)
            }
            Constants.HEALTH->{
                val data = intent?.getParcelableExtra<Article>(Constants.HEALTH)
                displayDetail(data)
            }
            Constants.SCIENCE->{
                val data = intent?.getParcelableExtra<Article>(Constants.SCIENCE)
                displayDetail(data)
            }
            Constants.SPORTS->{
                val data = intent?.getParcelableExtra<Article>(Constants.SPORTS)
                displayDetail(data)
            }
            Constants.TECHNOLOGY->{
                val data = intent?.getParcelableExtra<Article>(Constants.TECHNOLOGY)
                displayDetail(data)
            }
            Constants.VIVA->{
                val data = intent?.getParcelableExtra<Article>(Constants.VIVA)
                displayDetail(data)
            }
            Constants.SUARA->{
                val data = intent?.getParcelableExtra<Article>(Constants.SUARA)
                displayDetail(data)
            }
            Constants.DETIK->{
                val data = intent?.getParcelableExtra<Article>(Constants.DETIK)
                displayDetail(data)
            }
            Constants.KAPAN_LAGI->{
                val data = intent?.getParcelableExtra<Article>(Constants.KAPAN_LAGI)
                displayDetail(data)
            }
            Constants.TOP_HEADLINE->{
                val data = intent?.getParcelableExtra<Article>(Constants.TOP_HEADLINE)
                displayDetail(data)
            }
        }
    }

    private fun displayDetail(article: Article?) {
        article?.apply {
            binding.apply {
                Glide.with(this@DetailActivity)
                    .load(article.urlToImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.ic_baseline_broken_image_24)
                    .into(imageNews)
                titleNews.text = article.title
                sourceNews.text = article.source?.name
                dateNews.text = article.publishedAt
                contentNews.text = article.content
                onClick(article.url)
            }
        }
    }


}