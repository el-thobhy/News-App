package com.elthobhy.newsapp.viewmodel.kapanlagi

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.entity.headline.ArticleHeadline
import com.elthobhy.newsapp.data.source.local.entity.kapanlagi.ArticleKapanlagi
import com.elthobhy.newsapp.utils.vo.Resource

class KapanlagiViewModel(private val catalogRepo:CatalogNewsRepository):ViewModel() {
    fun getKapanlagiNews(): LiveData<Resource<List<ArticleKapanlagi>>> =
        catalogRepo.getKapanlagiNews()
}