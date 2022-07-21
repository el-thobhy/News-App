package com.elthobhy.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.entity.ArticleDetik
import com.elthobhy.newsapp.data.source.local.entity.ArticleHeadline
import com.elthobhy.newsapp.utils.vo.Resource

class DetikViewModel(private val catalogRepo: CatalogNewsRepository): ViewModel()  {
    fun getDetikNews(): LiveData<Resource<List<ArticleDetik>>> =
    catalogRepo.getDetikNews()
}