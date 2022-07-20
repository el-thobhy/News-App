package com.elthobhy.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.entity.Article

class BusinessViewModel(private val catalogRepo: CatalogNewsRepository): ViewModel() {
    fun getBusinessNews(): LiveData<List<Article>> =
        catalogRepo.getBusinessNews()
}