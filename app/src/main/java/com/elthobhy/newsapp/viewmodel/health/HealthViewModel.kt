package com.elthobhy.newsapp.viewmodel.health

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.entity.health.ArticleHealth
import com.elthobhy.newsapp.utils.vo.Resource

class HealthViewModel(private val catalogRepo: CatalogNewsRepository): ViewModel() {
    fun getHealthNews(): LiveData<Resource<List<ArticleHealth>>> =
        catalogRepo.getHealthNews()
}