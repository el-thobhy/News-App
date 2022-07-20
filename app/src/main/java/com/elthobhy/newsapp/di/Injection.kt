package com.elthobhy.newsapp.di

import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.remote.RemoteData

object Injection {
    fun provideCatalogRepository(): CatalogNewsRepository {
        val remoteData = RemoteData.getInstance()
        return CatalogNewsRepository.getInstance(remoteData)
    }
}