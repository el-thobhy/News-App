package com.elthobhy.newsapp.viewmodel.entertainment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.entity.entertainment.ArticleEntertainment
import com.elthobhy.newsapp.utils.vo.Resource

class EntertainmentViewModel(private val catalogRepo: CatalogNewsRepository): ViewModel() {
    fun getEntertainment(): LiveData<Resource<List<ArticleEntertainment>>> =
        catalogRepo.getEntertainmentNews()
}