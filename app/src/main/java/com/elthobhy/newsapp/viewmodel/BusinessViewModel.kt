package com.elthobhy.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.entity.ArticleBusiness
import com.elthobhy.newsapp.data.source.local.entity.ArticleHeadline
import com.elthobhy.newsapp.utils.vo.Resource

class BusinessViewModel(private val catalogRepo: CatalogNewsRepository): ViewModel() {
    fun getBusinessNews(): LiveData<Resource<List<ArticleBusiness>>> =
        catalogRepo.getBusinessNews()
}