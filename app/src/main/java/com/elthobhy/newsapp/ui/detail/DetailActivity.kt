package com.elthobhy.newsapp.ui.detail

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elthobhy.core.domain.model.Domain
import com.elthobhy.core.utils.Constants
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.databinding.ActivityDetailBinding
import com.elthobhy.newsapp.viewmodel.detail.DetailViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by inject<DetailViewModel>()
    private val listKey = arrayListOf(
        Constants.TOP_HEADLINE,
        Constants.TECHNOLOGY
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        for (key in listKey) {
            showDetail(key)
        }

    }

    private fun onClick(url: String?) {
        binding.goToLink.setOnClickListener {
            val intent = CustomTabsIntent.Builder().build()
            intent.launchUrl(this,Uri.parse(url))
        }

    }

    private fun setActionButton(
        headline: Domain?,
    ) {
        binding.bookmark.setOnClickListener {
            setBookmark(
                headline,
            )
        }
    }

    private fun setBookmark(
        headline: Domain?,
    ) {
            if(headline!= null)  {
                if (headline.bookmarked) {
                    showSnackBar("${headline.title} Removed from favorite")
                } else {
                    showSnackBar("${headline.title} Added to favorite")
                }
//                detailViewModel.setBookmarkedHeadline(headline)
            }
            else{
                Log.e("bookmark", "setBookmark: semua null", )
            }
    }
    private fun showSnackBar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG)
            .show()
    }

    private fun setBookmarkedState(isFavorite: Boolean) {
        if (isFavorite) {
            binding.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24)
        } else {
            binding.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
        }
    }

    private fun showDetail(key: String) {
        when (key) {
            Constants.TOP_HEADLINE -> {
                val dataIntent = intent?.getParcelableExtra<Domain>(Constants.TOP_HEADLINE)
                dataIntent?.title?.let { data ->
                    detailViewModel.getHeadline(data).observe(this){
                        displayDetail(it)
                        setActionButton(headline = it)
                    }
                }
            }
            Constants.TECHNOLOGY -> {
                val dataIntent = intent?.getParcelableExtra<Domain>(Constants.TECHNOLOGY)
                dataIntent?.title?.let { data ->
                    detailViewModel.getHeadline(data).observe(this){
                        displayDetail(it)
                        setActionButton(headline = it)
                    }
                }
            }
        }
    }


    private fun displayDetail(article: Domain) {
        binding.apply {
            if(article.urlToImage.isNullOrEmpty()){
                lottie.visibility= View.VISIBLE
                imageNews.visibility= View.GONE
            }else{
                lottie.visibility= View.GONE
                imageNews.visibility= View.VISIBLE
                Glide.with(this@DetailActivity)
                    .load(article.urlToImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.ic_baseline_broken_image_24)
                    .into(imageNews)
            }

            titleNews.text = article.title
            sourceNews.text = article.source?.name
            val secondApiFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            val time = LocalDate.parse(article.publishedAt, secondApiFormat)
            dateNews.text = time.toString()
            contentNews.text = article.content
            onClick(article.url)
            setBookmarkedState(article.bookmarked)
        }
    }


}