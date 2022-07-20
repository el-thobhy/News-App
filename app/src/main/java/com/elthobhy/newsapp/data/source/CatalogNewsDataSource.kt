package com.elthobhy.newsapp.data.source

import androidx.lifecycle.LiveData
import com.elthobhy.newsapp.data.source.local.entity.Article

interface CatalogNewsDataSource {
    fun getTopHeadlines(): LiveData<List<Article>>
    fun getDetikNews(): LiveData<List<Article>>
    fun getVivaNews(): LiveData<List<Article>>
    fun getKapanlagiNews(): LiveData<List<Article>>
    fun getSuaraNews(): LiveData<List<Article>>
    fun getBusinessNews(): LiveData<List<Article>>
    fun getEntertainmentNews(): LiveData<List<Article>>
    fun getGeneralNews(): LiveData<List<Article>>
    fun getHealthNews(): LiveData<List<Article>>
    fun getScienceNews(): LiveData<List<Article>>
    fun getSportsNews(): LiveData<List<Article>>
    fun getTechnologyNews(): LiveData<List<Article>>
}