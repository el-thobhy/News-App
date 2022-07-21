package com.elthobhy.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.entity.ArticleHeadline
import com.elthobhy.newsapp.data.source.local.entity.ArticleViva
import com.elthobhy.newsapp.utils.vo.Resource

class VivaViewModel(private val catalogRepo: CatalogNewsRepository): ViewModel() {
    fun getVivaNews(): LiveData<Resource<List<ArticleViva>>> =
        catalogRepo.getVivaNews()
}