package com.elthobhy.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.entity.ArticleHeadline
import com.elthobhy.newsapp.data.source.local.entity.ArticleScience
import com.elthobhy.newsapp.utils.vo.Resource

class ScienceViewModel(private val catalogRepo: CatalogNewsRepository): ViewModel() {
    fun getScienceNews(): LiveData<Resource<List<ArticleScience>>> =
        catalogRepo.getScienceNews()
}