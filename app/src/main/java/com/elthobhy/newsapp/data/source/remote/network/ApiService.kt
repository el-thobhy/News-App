package com.elthobhy.newsapp.data.source.remote.network

import com.elthobhy.newsapp.BuildConfig
import com.elthobhy.newsapp.data.source.remote.response.ArticlesItem
import com.elthobhy.newsapp.data.source.remote.response.ResponseTopHeadlines
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines?apiKey=${BuildConfig.API_KEY}")
    fun getTopHeadlines(
        @Query("country") country: String
    ): Call<ResponseTopHeadlines<ArticlesItem>>
}