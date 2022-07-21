package com.elthobhy.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.entity.ArticleGeneral
import com.elthobhy.newsapp.data.source.local.entity.ArticleHeadline
import com.elthobhy.newsapp.utils.vo.Resource

class GeneralViewModel(private val catalogRepo: CatalogNewsRepository): ViewModel(){
fun getGeneralNews(): LiveData<Resource<List<ArticleGeneral>>> =
    catalogRepo.getGeneralNews()
}