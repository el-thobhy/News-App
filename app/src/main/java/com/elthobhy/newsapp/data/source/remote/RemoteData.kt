package com.elthobhy.newsapp.data.source.remote

import android.util.Log
import com.elthobhy.newsapp.data.source.remote.network.ApiConfig
import com.elthobhy.newsapp.data.source.remote.response.ArticlesItem
import com.elthobhy.newsapp.data.source.remote.response.ResponseTopHeadlines
import com.elthobhy.newsapp.utils.EspressoIdlingResource
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class RemoteData private constructor(){
    companion object{
        @Volatile
        private var instance: RemoteData? = null

        fun getInstance(): RemoteData {
            return instance ?: synchronized(this){
                instance ?: RemoteData().apply {
                    instance = this
                }
            }
        }
    }
    fun getTopHeadlines(
        callback: LoadTopHeadlinesCallback
    ){
        EspressoIdlingResource.increment()
        ApiConfig.getApiServeice().getTopHeadlines("id")
            .enqueue(object : Callback<ResponseTopHeadlines<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    response: Response<ResponseTopHeadlines<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        response.body()?.articles?.let { articleItem->
                            callback.onAllTopHeadlinesReceived(articleItem)
                        }
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    callback.onAllTopHeadlinesReceived(emptyList())
                    EspressoIdlingResource.decrement()
                }

            })

    }

    fun getDetikNews(
        callback: LoadDetikNewsCallback
    ){
        EspressoIdlingResource.increment()
        ApiConfig.getApiServeice().getDetikNews("detik.com")
            .enqueue(object : Callback<ResponseTopHeadlines<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    response: Response<ResponseTopHeadlines<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        response.body()?.articles?.let { articleItem->
                            callback.onAllDetikReceived(articleItem)
                        }
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    callback.onAllDetikReceived(emptyList())
                    EspressoIdlingResource.decrement()
                }

            })
    }

    interface LoadTopHeadlinesCallback{
        fun onAllTopHeadlinesReceived(topResponse: List<ArticlesItem?>)
    }

    interface LoadDetikNewsCallback{
        fun onAllDetikReceived(detikResponse: List<ArticlesItem?>)
    }
}