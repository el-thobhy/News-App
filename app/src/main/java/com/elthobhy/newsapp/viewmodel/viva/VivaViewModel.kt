package com.elthobhy.newsapp.viewmodel.viva

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.entity.viva.ArticleViva
import com.elthobhy.newsapp.utils.vo.Resource

class VivaViewModel(private val catalogRepo: CatalogNewsRepository): ViewModel() {
    fun getVivaNews(): LiveData<Resource<List<ArticleViva>>> =
        catalogRepo.getVivaNews()
}