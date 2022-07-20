package com.elthobhy.newsapp.data.source

import androidx.lifecycle.LiveData
import com.elthobhy.newsapp.data.source.local.entity.Article

interface CatalogNewsDataSource {
    fun getTopHeadlines(): LiveData<List<Article>>
}