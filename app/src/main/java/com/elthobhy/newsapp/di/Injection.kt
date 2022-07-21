package com.elthobhy.newsapp.di

import android.content.Context
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.LocalData
import com.elthobhy.newsapp.data.source.local.room.ArticleDatabase
import com.elthobhy.newsapp.data.source.remote.RemoteData
import com.elthobhy.newsapp.utils.AppExecutors

object Injection {
    fun provideCatalogRepository(context: Context): CatalogNewsRepository {
        val database = ArticleDatabase.getInstance(context)
        val remoteData = RemoteData.getInstance()
        val localData = LocalData.getInstace(database.articleDao())
        val appExecutors = AppExecutors()
        return CatalogNewsRepository.getInstance(remoteData, localData, appExecutors)
    }
}