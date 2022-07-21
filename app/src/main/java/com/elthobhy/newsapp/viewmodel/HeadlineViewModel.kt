package com.elthobhy.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.entity.ArticleHeadline
import com.elthobhy.newsapp.utils.vo.Resource

class HeadlineViewModel(private val catalogRepo: CatalogNewsRepository): ViewModel() {

    fun getHeadline(): LiveData<Resource<List<ArticleHeadline>>> =
        catalogRepo.getTopHeadlines()
}