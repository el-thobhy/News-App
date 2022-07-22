package com.elthobhy.newsapp.viewmodel.technology

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.entity.headline.ArticleHeadline
import com.elthobhy.newsapp.data.source.local.entity.technology.ArticleTechnology
import com.elthobhy.newsapp.utils.vo.Resource

class TechnologyViewModel(private val catalogRepo: CatalogNewsRepository): ViewModel() {
    fun getTechnologyNews(): LiveData<Resource<List<ArticleTechnology>>> =
        catalogRepo.getTechnologyNews()
}