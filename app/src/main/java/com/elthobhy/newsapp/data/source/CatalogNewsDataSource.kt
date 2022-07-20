package com.elthobhy.newsapp.data.source

import androidx.lifecycle.LiveData
import com.elthobhy.newsapp.data.source.local.entity.Article

interface CatalogNewsDataSource {
    fun getTopHeadlines(): LiveData<List<Article>>
    fun getDetikNews(): LiveData<List<Article>>
    fun getVivaNews(): LiveData<List<Article>>
    fun getKapanlagiNews(): LiveData<List<Article>>
}