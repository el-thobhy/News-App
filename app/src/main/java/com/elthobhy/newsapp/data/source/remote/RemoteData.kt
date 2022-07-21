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
        ApiConfig.getApiServeice().getDomainNews("detik.com")
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

    fun getVivaNews(
        callback: LoadVivaNewsCallback
    ){
        EspressoIdlingResource.increment()
        ApiConfig.getApiServeice().getDomainNews("viva.co.id")
            .enqueue(object : Callback<ResponseTopHeadlines<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    response: Response<ResponseTopHeadlines<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        response.body()?.articles?.let { articleItem->
                            callback.onAllVivaReceived(articleItem)
                        }
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    callback.onAllVivaReceived(emptyList())
                    EspressoIdlingResource.decrement()
                }

            })
    }

    fun getKapanlagiNews(
        callback: LoadKapanlagiCallback
    ){
        EspressoIdlingResource.increment()
        ApiConfig.getApiServeice().getDomainNews("kapanlagi.com")
            .enqueue(object : Callback<ResponseTopHeadlines<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    response: Response<ResponseTopHeadlines<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        response.body()?.articles?.let { articleItem->
                            callback.onAllKapanlagiReceived(articleItem)
                        }
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    callback.onAllKapanlagiReceived(emptyList())
                    EspressoIdlingResource.decrement()
                }

            })
    }

    fun getSuaraNews(
        callback: LoadSuaraNewsCallback
    ){
        EspressoIdlingResource.increment()
        ApiConfig.getApiServeice().getDomainNews("suara.com")
            .enqueue(object : Callback<ResponseTopHeadlines<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    response: Response<ResponseTopHeadlines<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        response.body()?.articles?.let { articleItem->
                            callback.onAllSuaraReceived(articleItem)
                        }
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    callback.onAllSuaraReceived(emptyList())
                    EspressoIdlingResource.decrement()
                }

            })
    }

    fun getBusinessNews(
        callback: LoadBusinessCallback
    ){
        EspressoIdlingResource.increment()
        ApiConfig.getApiServeice().getCategoryNews("business")
            .enqueue(object : Callback<ResponseTopHeadlines<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    response: Response<ResponseTopHeadlines<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        response.body()?.articles?.let { articleItem->
                            callback.onAllBusinessReceived(articleItem)
                        }
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    callback.onAllBusinessReceived(emptyList())
                    EspressoIdlingResource.decrement()
                }

            })
    }
    fun getEntertainmentNews(
        callback: LoadEntertainmentCallback
    ) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiServeice().getCategoryNews("entertainment")
            .enqueue(object : Callback<ResponseTopHeadlines<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    response: Response<ResponseTopHeadlines<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        response.body()?.articles?.let { articleItem->
                            callback.onAllEntertainmentReceived(articleItem)
                        }
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    callback.onAllEntertainmentReceived(emptyList())
                    EspressoIdlingResource.decrement()
                }

            })
    }
    fun getGeneralNews(
        callback: LoadGeneralCallback
    ) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiServeice().getCategoryNews("general")
            .enqueue(object : Callback<ResponseTopHeadlines<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    response: Response<ResponseTopHeadlines<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        response.body()?.articles?.let { articleItem->
                            callback.onAllGeneralReceived(articleItem)
                        }
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    callback.onAllGeneralReceived(emptyList())
                    EspressoIdlingResource.decrement()
                }

            })
    }
    fun getHealthNews(
        callback: LoadHealthCallback
    ) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiServeice().getCategoryNews("health")
            .enqueue(object : Callback<ResponseTopHeadlines<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    response: Response<ResponseTopHeadlines<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        response.body()?.articles?.let { articleItem->
                            callback.onAllHealthReceived(articleItem)
                        }
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    callback.onAllHealthReceived(emptyList())
                    EspressoIdlingResource.decrement()
                }

            })
    }
    fun getScienceNews(
        callback: LoadScienceCallback
    ) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiServeice().getCategoryNews("science")
            .enqueue(object : Callback<ResponseTopHeadlines<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    response: Response<ResponseTopHeadlines<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        response.body()?.articles?.let { articleItem->
                            callback.onAllScienceReceived(articleItem)
                        }
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    callback.onAllScienceReceived(emptyList())
                    EspressoIdlingResource.decrement()
                }

            })
    }
    fun getSportsNews(
        callback: LoadSportsCallback
    ) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiServeice().getCategoryNews("sports")
            .enqueue(object : Callback<ResponseTopHeadlines<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    response: Response<ResponseTopHeadlines<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        response.body()?.articles?.let { articleItem->
                            callback.onAllSportsReceived(articleItem)
                        }
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    callback.onAllSportsReceived(emptyList())
                    EspressoIdlingResource.decrement()
                }

            })
    }
    fun getTechnologyNews(
        callback: LoadTechnologyCallback
    ) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiServeice().getCategoryNews("technology")
            .enqueue(object : Callback<ResponseTopHeadlines<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    response: Response<ResponseTopHeadlines<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        response.body()?.articles?.let { articleItem->
                            callback.onAllTechnologyReceived(articleItem)
                        }
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseTopHeadlines<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    callback.onAllTechnologyReceived(emptyList())
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
    interface LoadVivaNewsCallback{
        fun onAllVivaReceived(vivaResponse: List<ArticlesItem?>)
    }
    interface LoadKapanlagiCallback{
        fun onAllKapanlagiReceived(kapanlagiResponse: List<ArticlesItem?>)
    }
    interface LoadSuaraNewsCallback{
        fun onAllSuaraReceived(suaraResponse: List<ArticlesItem?>)
    }
    interface LoadBusinessCallback{
        fun onAllBusinessReceived(businessResponse: List<ArticlesItem?>)
    }
    interface LoadEntertainmentCallback{
        fun onAllEntertainmentReceived(entertainmentResponse: List<ArticlesItem?>)
    }
    interface LoadGeneralCallback{
        fun onAllGeneralReceived(generalResponse: List<ArticlesItem?>)
    }
    interface LoadHealthCallback{
        fun onAllHealthReceived(healthResponse: List<ArticlesItem?>)
    }
    interface LoadScienceCallback{
        fun onAllScienceReceived(scienceResponse: List<ArticlesItem?>)
    }
    interface LoadSportsCallback{
        fun onAllSportsReceived(sportsResponse: List<ArticlesItem?>)
    }
    interface LoadTechnologyCallback{
        fun onAllTechnologyReceived(technologyResponse: List<ArticlesItem?>)
    }
}