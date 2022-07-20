package com.elthobhy.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.entity.Article
import com.elthobhy.newsapp.utils.DataDummy

class HeadlineViewModel(private val catalogRepo: CatalogNewsRepository): ViewModel() {

    fun getHeadline(): LiveData<List<Article>> =
        catalogRepo.getTopHeadlines()
}