package com.elthobhy.newsapp.viewmodel.sports

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.entity.headline.ArticleHeadline
import com.elthobhy.newsapp.data.source.local.entity.sports.ArticleSports
import com.elthobhy.newsapp.utils.vo.Resource

class SportViewModel(private val catalogRepo: CatalogNewsRepository): ViewModel() {
    fun getSportsNews(): LiveData<Resource<List<ArticleSports>>> =
        catalogRepo.getSportsNews()
}