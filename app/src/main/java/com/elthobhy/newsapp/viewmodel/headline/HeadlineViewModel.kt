package com.elthobhy.newsapp.viewmodel.headline

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.entity.headline.ArticleHeadline
import com.elthobhy.newsapp.utils.vo.Resource

class HeadlineViewModel(private val catalogRepo: CatalogNewsRepository): ViewModel() {

    fun getHeadline(): LiveData<Resource<List<ArticleHeadline>>> =
        catalogRepo.getTopHeadlines()
}