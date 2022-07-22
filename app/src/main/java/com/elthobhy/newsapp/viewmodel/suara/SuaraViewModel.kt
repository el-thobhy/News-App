package com.elthobhy.newsapp.viewmodel.suara

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.entity.headline.ArticleHeadline
import com.elthobhy.newsapp.data.source.local.entity.suara.ArticleSuara
import com.elthobhy.newsapp.utils.vo.Resource

class SuaraViewModel(private val catalogRepo: CatalogNewsRepository): ViewModel() {
    fun getSuaraNews(): LiveData<Resource<List<ArticleSuara>>> =
       catalogRepo.getSuaraNews()

}