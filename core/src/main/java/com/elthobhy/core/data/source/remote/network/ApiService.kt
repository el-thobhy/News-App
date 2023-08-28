package com.elthobhy.core.data.source.remote.network

import com.elthobhy.core.BuildConfig
import com.elthobhy.core.data.source.remote.response.ArticlesItem
import com.elthobhy.core.data.source.remote.response.ResponseCatalog
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything?apiKey=${BuildConfig.API_KEY}")
    suspend fun getTopHeadlines(
        @Query("q") country: String
    ): ResponseCatalog<ArticlesItem>

    @GET("everything?apiKey=${BuildConfig.API_KEY}")
    fun getDomainNews(
        @Query("domains") domains: String
    ): Call<ResponseCatalog<ArticlesItem>>

    @GET("everything?apiKey=${BuildConfig.API_KEY}")
    suspend fun getSearch(
        @Query("qInTitle") category: String
    ): ResponseCatalog<ArticlesItem>
}