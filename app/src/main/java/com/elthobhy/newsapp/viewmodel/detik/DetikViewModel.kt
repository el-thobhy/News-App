package com.elthobhy.newsapp.viewmodel.detik

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.entity.detik.ArticleDetik
import com.elthobhy.newsapp.utils.vo.Resource

class DetikViewModel(private val catalogRepo: CatalogNewsRepository): ViewModel()  {
    fun getDetikNews(): LiveData<Resource<List<ArticleDetik>>> =
    catalogRepo.getDetikNews()
}