package com.elthobhy.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.entity.ArticleHeadline
import com.elthobhy.newsapp.data.source.local.entity.ArticleKapanlagi
import com.elthobhy.newsapp.utils.vo.Resource

class KapanlagiViewModel(private val catalogRepo:CatalogNewsRepository):ViewModel() {
    fun getKapanlagiNews(): LiveData<Resource<List<ArticleKapanlagi>>> =
        catalogRepo.getKapanlagiNews()
}