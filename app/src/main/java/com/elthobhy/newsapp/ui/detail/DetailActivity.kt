package com.elthobhy.newsapp.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.business.ArticleBusiness
import com.elthobhy.newsapp.data.source.local.entity.detik.ArticleDetik
import com.elthobhy.newsapp.data.source.local.entity.entertainment.ArticleEntertainment
import com.elthobhy.newsapp.data.source.local.entity.general.ArticleGeneral
import com.elthobhy.newsapp.data.source.local.entity.headline.ArticleHeadline
import com.elthobhy.newsapp.data.source.local.entity.health.ArticleHealth
import com.elthobhy.newsapp.data.source.local.entity.kapanlagi.ArticleKapanlagi
import com.elthobhy.newsapp.data.source.local.entity.science.ArticleScience
import com.elthobhy.newsapp.data.source.local.entity.sports.ArticleSports
import com.elthobhy.newsapp.data.source.local.entity.suara.ArticleSuara
import com.elthobhy.newsapp.data.source.local.entity.technology.ArticleTechnology
import com.elthobhy.newsapp.data.source.local.entity.viva.ArticleViva
import com.elthobhy.newsapp.databinding.ActivityDetailBinding
import com.elthobhy.newsapp.utils.Constants
import com.elthobhy.newsapp.viewmodel.*
import com.elthobhy.newsapp.viewmodel.detail.DetailViewModel
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private val listKey = arrayListOf(
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        for (key in listKey) {
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

    private fun setActionButton(
        health: ArticleHealth?,
        headline: ArticleHeadline?,
        business: ArticleBusiness?,
        entertainment: ArticleEntertainment?,
        general: ArticleGeneral?,
        science: ArticleScience?,
        sports: ArticleSports?,
        technology: ArticleTechnology?,
        viva: ArticleViva?,
        detik: ArticleDetik?,
        kapanlagi: ArticleKapanlagi?,
        suara: ArticleSuara?,
    ) {
        binding.bookmark.setOnClickListener {
            setBookmark(
                health,
                headline,
                business,
                entertainment,
                general,
                science,
                sports,
                technology,
                viva,
                detik,
                kapanlagi,
                suara
            )
        }
    }

    private fun setBookmark(
        health: ArticleHealth?,
        headline: ArticleHeadline?,
        business: ArticleBusiness?,
        entertainment: ArticleEntertainment?,
        general: ArticleGeneral?,
        science: ArticleScience?,
        sports: ArticleSports?,
        technology: ArticleTechnology?,
        viva: ArticleViva?,
        detik: ArticleDetik?,
        kapanlagi: ArticleKapanlagi?,
        suara: ArticleSuara?
    ) {

            if(health != null)  {
                if (health.bookmarked) {
                    showSnackBar("${health.source} Removed from favorite")
                } else {
                    showSnackBar("${health.source} Added to favorite")
                }
                detailViewModel.setBookmarkedHealth(health)
            }
            else if(headline!= null)  {
                if (headline.bookmarked) {
                    showSnackBar("${headline.title} Removed from favorite")
                } else {
                    showSnackBar("${headline.title} Added to favorite")
                }
                detailViewModel.setBookmarkedHeadline(headline)
            }
            else if(business != null) {
                if (business.bookmarked) {
                    showSnackBar("${business.source} Removed from favorite")
                } else {
                    showSnackBar("${business.source} Added to favorite")
                }
                detailViewModel.setBookmarkedBusiness(business)
            }
            else if(entertainment!= null)  {
                if (entertainment.bookmarked) {
                    showSnackBar("${entertainment.title} Removed from favorite")
                } else {
                    showSnackBar("${entertainment.title} Added to favorite")
                }
                detailViewModel.setBookmarkedEntertainment(entertainment)
            }
            else if(general != null) {
                if (general.bookmarked) {
                    showSnackBar("${general.title} Removed from favorite")
                } else {
                    showSnackBar("${general.title} Added to favorite")
                }
                detailViewModel.setBookmarkedGeneral(general)
            }
            else if(science!= null)  {
                if (science.bookmarked) {
                    showSnackBar("${science.title} Removed from favorite")
                } else {
                    showSnackBar("${science.title} Added to favorite")
                }
                detailViewModel.setBookmarkedScience(science)
            }
            else if(sports != null) {
                if (sports.bookmarked) {
                    showSnackBar("${sports.title} Removed from favorite")
                } else {
                    showSnackBar("${sports.title} Added to favorite")
                }
                detailViewModel.setBookmarkedSports(sports)
            }
            else if(technology != null) {
                if (technology.bookmarked) {
                    showSnackBar("${technology.title} Removed from favorite")
                } else {
                    showSnackBar("${technology.title} Added to favorite")
                }
                detailViewModel.setBookmarkedTechnology(technology)
            }
            else if(viva != null) {
                if (viva.bookmarked) {
                    showSnackBar("${viva.title} Removed from favorite")
                } else {
                    showSnackBar("${viva.title} Added to favorite")
                }
                detailViewModel.setBookmarkedViva(viva)
            }
            else if(detik != null) {
                if (detik.bookmarked) {
                    showSnackBar("${detik.title} Removed from favorite")
                } else {
                    showSnackBar("${detik.title} Added to favorite")
                }
                detailViewModel.setBookmarkedDetik(detik)
            }
            else if(kapanlagi != null) {
                if (kapanlagi.bookmarked) {
                    showSnackBar("${kapanlagi.title} Removed from favorite")
                } else {
                    showSnackBar("${kapanlagi.title} Added to favorite")
                }
                detailViewModel.setBookmarkedKapanlagi(kapanlagi)
            }
            else if(suara != null) {
                if (suara.bookmarked) {
                    showSnackBar("${suara.title} Removed from favorite")
                } else {
                    showSnackBar("${suara.title} Added to favorite")
                }
                detailViewModel.setBookmarkedSuara(suara)
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
            Constants.BUSINESS -> {
                val data = intent?.getParcelableExtra<ArticleBusiness>(Constants.BUSINESS)
                if (data != null) {
                    detailViewModel.getBusinessNews(data.content).observe(this){
                        setActionButton(business = it ,
                            health = null, entertainment = null,
                            headline = null, general = null, science = null,
                            sports = null, technology = null, viva = null, detik = null,
                            kapanlagi = null, suara = null
                        )
                        displayDetail(it)
                    }
                }
            }
            Constants.ENTERTAINMENT -> {
                val data = intent?.getParcelableExtra<ArticleEntertainment>(Constants.ENTERTAINMENT)
                if (data != null) {
                    detailViewModel.getEntertainment(data.content).observe(this){
                        displayDetail(it)
                        setActionButton(entertainment = it,business = null,
                            health = null,
                            headline = null, general = null, science = null,
                            sports = null, technology = null, viva = null, detik = null,
                            kapanlagi = null, suara = null)
                    }
                }

            }
            Constants.GENERAL -> {
                val data = intent?.getParcelableExtra<ArticleGeneral>(Constants.GENERAL)
                if (data != null) {
                    detailViewModel.getGeneralNews(data.content).observe(this){
                        displayDetail(it)
                        setActionButton(general = it,science = null,
                            sports = null, technology = null, viva = null, detik = null,
                            kapanlagi = null, suara = null,entertainment = null,business = null,
                            health = null,
                            headline = null,)
                    }
                }
            }
            Constants.HEALTH -> {
                val data = intent?.getParcelableExtra<ArticleHealth>(Constants.HEALTH)
                if (data != null) {
                    detailViewModel.getHealthNews(data.content).observe(this){
                        displayDetail(it)
                        setActionButton(general = null,science = null,
                            sports = null, technology = null, viva = null, detik = null,
                            kapanlagi = null, suara = null,entertainment = null,business = null,
                            health = it,
                            headline = null,)
                    }
                }
            }
            Constants.SCIENCE -> {
                val data = intent?.getParcelableExtra<ArticleScience>(Constants.SCIENCE)
                if (data != null) {
                    detailViewModel.getScienceNews(data.content).observe(this){
                        displayDetail(it)
                        setActionButton(general = null,science = it,
                            sports = null, technology = null, viva = null, detik = null,
                            kapanlagi = null, suara = null,entertainment = null,business = null,
                            health = null,
                            headline = null,)
                    }
                }
            }
            Constants.SPORTS -> {
                val data = intent?.getParcelableExtra<ArticleSports>(Constants.SPORTS)
                if (data != null) {
                    detailViewModel.getSportsNews(data.content).observe(this){
                        displayDetail(it)
                        setActionButton(general = null,science = null,
                            sports = it, technology = null, viva = null, detik = null,
                            kapanlagi = null, suara = null,entertainment = null,business = null,
                            health = null,
                            headline = null,)
                    }
                }

            }
            Constants.TECHNOLOGY -> {
                val data = intent?.getParcelableExtra<ArticleTechnology>(Constants.TECHNOLOGY)
                if (data != null) {
                    detailViewModel.getTechnologyNews(data.content).observe(this){
                        displayDetail(it)
                        setActionButton(general = null,science = null,
                            sports = null, technology = it, viva = null, detik = null,
                            kapanlagi = null, suara = null,entertainment = null,business = null,
                            health = null,
                            headline = null,)
                    }
                }

            }
            Constants.VIVA -> {
                val data = intent?.getParcelableExtra<ArticleViva>(Constants.VIVA)
                if (data != null) {
                    detailViewModel.getVivaNews(data.content).observe(this){
                        displayDetail(it)
                        setActionButton(general = null,science = null,
                            sports = null, technology = null, viva = it, detik = null,
                            kapanlagi = null, suara = null,entertainment = null,business = null,
                            health = null,
                            headline = null,)
                    }
                }


            }
            Constants.SUARA -> {
                val data = intent?.getParcelableExtra<ArticleSuara>(Constants.SUARA)
                if (data != null) {
                    detailViewModel.getSuaraNews(data.content).observe(this){
                        displayDetail(it)
                        setActionButton(general = null,science = null,
                            sports = null, technology = null, viva = null, detik = null,
                            kapanlagi = null, suara = it,entertainment = null,business = null,
                            health = null,
                            headline = null,)
                    }
                }


            }
            Constants.DETIK -> {
                val data = intent?.getParcelableExtra<ArticleDetik>(Constants.DETIK)
                if (data != null) {
                    detailViewModel.getDetikNews(data.content).observe(this){
                        displayDetail(it)
                        setActionButton(general = null,science = null,
                            sports = null, technology = null, viva = null, detik = it,
                            kapanlagi = null, suara = null,entertainment = null,business = null,
                            health = null,
                            headline = null,)
                    }
                }


            }
            Constants.KAPAN_LAGI -> {
                val data = intent?.getParcelableExtra<ArticleKapanlagi>(Constants.KAPAN_LAGI)
                if (data != null) {
                    detailViewModel.getKapanlagiNews(data.content).observe(this){
                        displayDetail(it)
                        setActionButton(general = null,science = null,
                            sports = null, technology = null, viva = null, detik = null,
                            kapanlagi = it, suara = null,entertainment = null,business = null,
                            health = null,
                            headline = null,)
                    }
                }


            }
            Constants.TOP_HEADLINE -> {
                val data = intent?.getParcelableExtra<ArticleHeadline>(Constants.TOP_HEADLINE)
                if (data != null) {
                    detailViewModel.getHeadline(data.content).observe(this){
                        displayDetail(it)
                        setActionButton(general = null,science = null,
                            sports = null, technology = null, viva = null, detik = null,
                            kapanlagi = null, suara = null,entertainment = null,business = null,
                            health = null,
                            headline = it,)
                    }
                }


            }
        }
    }

    private fun displayDetail(article: Parcelable?) {
        when (article) {
            is ArticleKapanlagi -> {
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
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleSuara -> {
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
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleDetik -> {
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
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleViva -> {
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
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleTechnology -> {
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
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleSports -> {
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
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleScience -> {
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
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleGeneral -> {
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
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleEntertainment -> {
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
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleBusiness -> {
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
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleHeadline -> {
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
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleHealth -> {
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
                    setBookmarkedState(article.bookmarked)
                }
            }

        }


    }


}