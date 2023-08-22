package com.elthobhy.newsapp.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elthobhy.newsapp.data.source.remote.network.ApiConfig
import com.elthobhy.newsapp.data.source.remote.response.ArticlesItem
import com.elthobhy.newsapp.data.source.remote.response.ResponseCatalog
import com.elthobhy.newsapp.data.source.remote.response.vo.ApiResponse
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
    fun getTopHeadlines(): LiveData<ApiResponse<List<ArticlesItem>>>{
        EspressoIdlingResource.increment()
        val headlineResponse = MutableLiveData<ApiResponse<List<ArticlesItem>>>()
        ApiConfig.getApiServeice().getTopHeadlines("id")
            .enqueue(object : Callback<ResponseCatalog<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    response: Response<ResponseCatalog<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        headlineResponse.postValue(ApiResponse.success(response.body()?.articles as List<ArticlesItem>))
                        Log.d("tesdebug", "onResponse: ${response.body()}")
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    headlineResponse.postValue(
                        ApiResponse.error(
                            msg = t.message.toString(),
                            mutableListOf()
                        )
                    )
                    EspressoIdlingResource.decrement()
                }
            })
        return headlineResponse
    }

    fun getDetikNews(): LiveData<ApiResponse<List<ArticlesItem>>>{
        EspressoIdlingResource.increment()
        val detikResponse = MutableLiveData<ApiResponse<List<ArticlesItem>>>()
        ApiConfig.getApiServeice().getDomainNews("detik.com")
            .enqueue(object : Callback<ResponseCatalog<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    response: Response<ResponseCatalog<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        detikResponse.postValue(ApiResponse.success(response.body()?.articles as List<ArticlesItem>))
                        Log.d("tesdebug", "onResponse: $detikResponse")
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    detikResponse.postValue(
                        ApiResponse.error(
                            msg = t.message.toString(),
                            mutableListOf()
                        )
                    )
                    EspressoIdlingResource.decrement()
                }
            })
        return detikResponse
    }

    fun getVivaNews(): LiveData<ApiResponse<List<ArticlesItem>>>{
        EspressoIdlingResource.increment()
        val vivaResponse = MutableLiveData<ApiResponse<List<ArticlesItem>>>()
        ApiConfig.getApiServeice().getDomainNews("google.com")
            .enqueue(object : Callback<ResponseCatalog<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    response: Response<ResponseCatalog<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        vivaResponse.postValue(ApiResponse.success(response.body()?.articles as List<ArticlesItem>))
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    vivaResponse.postValue(
                        ApiResponse.error(
                            msg = t.message.toString(),
                            mutableListOf()
                        )
                    )
                    EspressoIdlingResource.decrement()
                }

            })
        return vivaResponse
    }

    fun getKapanlagiNews(): LiveData<ApiResponse<List<ArticlesItem>>>{
        EspressoIdlingResource.increment()
        val kapanlagiResponse = MutableLiveData<ApiResponse<List<ArticlesItem>>>()
        ApiConfig.getApiServeice().getDomainNews("kapanlagi.com")
            .enqueue(object : Callback<ResponseCatalog<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    response: Response<ResponseCatalog<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        kapanlagiResponse.postValue(ApiResponse.success(response.body()?.articles as List<ArticlesItem>))
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    kapanlagiResponse.postValue(
                        ApiResponse.error(
                            msg = t.message.toString(),
                            mutableListOf()
                        )
                    )
                    EspressoIdlingResource.decrement()
                }

            })
        return kapanlagiResponse
    }

    fun getSuaraNews(): LiveData<ApiResponse<List<ArticlesItem>>>{
        EspressoIdlingResource.increment()
        val suaraResponse = MutableLiveData<ApiResponse<List<ArticlesItem>>>()
        ApiConfig.getApiServeice().getDomainNews("suara.com")
            .enqueue(object : Callback<ResponseCatalog<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    response: Response<ResponseCatalog<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        suaraResponse.postValue(ApiResponse.success(response.body()?.articles as List<ArticlesItem>))
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    suaraResponse.postValue(
                        ApiResponse.error(
                            msg = t.message.toString(),
                            mutableListOf()
                        )
                    )
                    EspressoIdlingResource.decrement()
                }

            })
        return suaraResponse
    }

    fun getBusinessNews(): LiveData<ApiResponse<List<ArticlesItem>>>{
        EspressoIdlingResource.increment()
        val businessResponse = MutableLiveData<ApiResponse<List<ArticlesItem>>>()
        ApiConfig.getApiServeice().getCategoryNews("business")
            .enqueue(object : Callback<ResponseCatalog<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    response: Response<ResponseCatalog<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        businessResponse.postValue(ApiResponse.success(response.body()?.articles as List<ArticlesItem>))
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    businessResponse.postValue(
                        ApiResponse.error(
                            msg = t.message.toString(),
                            mutableListOf()
                        )
                    )
                    EspressoIdlingResource.decrement()
                }

            })
        return businessResponse
    }
    fun getEntertainmentNews(): LiveData<ApiResponse<List<ArticlesItem>>> {
        EspressoIdlingResource.increment()
        val entertainmentResponse = MutableLiveData<ApiResponse<List<ArticlesItem>>>()
        ApiConfig.getApiServeice().getCategoryNews("entertainment")
            .enqueue(object : Callback<ResponseCatalog<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    response: Response<ResponseCatalog<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        entertainmentResponse.postValue(ApiResponse.success(response.body()?.articles as List<ArticlesItem>))
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    entertainmentResponse.postValue(
                        ApiResponse.error(
                            msg = t.message.toString(),
                            mutableListOf()
                        )
                    )
                    EspressoIdlingResource.decrement()
                }

            })
        return entertainmentResponse
    }
    fun getGeneralNews(): LiveData<ApiResponse<List<ArticlesItem>>> {
        EspressoIdlingResource.increment()
        val generalResponse = MutableLiveData<ApiResponse<List<ArticlesItem>>>()
        ApiConfig.getApiServeice().getCategoryNews("general")
            .enqueue(object : Callback<ResponseCatalog<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    response: Response<ResponseCatalog<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        generalResponse.postValue(ApiResponse.success(response.body()?.articles as List<ArticlesItem>))
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    generalResponse.postValue(
                        ApiResponse.error(
                            msg = t.message.toString(),
                            mutableListOf()
                        )
                    )
                    EspressoIdlingResource.decrement()
                }

            })
        return generalResponse
    }
    fun getHealthNews(): LiveData<ApiResponse<List<ArticlesItem>>> {
        EspressoIdlingResource.increment()
        val healthResponse = MutableLiveData<ApiResponse<List<ArticlesItem>>>()
        ApiConfig.getApiServeice().getCategoryNews("health")
            .enqueue(object : Callback<ResponseCatalog<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    response: Response<ResponseCatalog<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        healthResponse.postValue(ApiResponse.success(response.body()?.articles as List<ArticlesItem>))
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    healthResponse.postValue(
                        ApiResponse.error(
                            msg = t.message.toString(),
                            mutableListOf()
                        )
                    )
                    EspressoIdlingResource.decrement()
                }

            })
        return healthResponse
    }
    fun getScienceNews(): LiveData<ApiResponse<List<ArticlesItem>>> {
        EspressoIdlingResource.increment()
        val scienceResponse = MutableLiveData<ApiResponse<List<ArticlesItem>>>()
        ApiConfig.getApiServeice().getCategoryNews("science")
            .enqueue(object : Callback<ResponseCatalog<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    response: Response<ResponseCatalog<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        scienceResponse.postValue(ApiResponse.success(response.body()?.articles as List<ArticlesItem>))
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    scienceResponse.postValue(
                        ApiResponse.error(
                            msg = t.message.toString(),
                            mutableListOf()
                        )
                    )
                    EspressoIdlingResource.decrement()
                }

            })
        return scienceResponse
    }
    fun getSportsNews(): LiveData<ApiResponse<List<ArticlesItem>>> {
        EspressoIdlingResource.increment()
        val sportsResponse = MutableLiveData<ApiResponse<List<ArticlesItem>>>()
        ApiConfig.getApiServeice().getCategoryNews("sports")
            .enqueue(object : Callback<ResponseCatalog<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    response: Response<ResponseCatalog<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        sportsResponse.postValue(ApiResponse.success(response.body()?.articles as List<ArticlesItem>))
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    sportsResponse.postValue(
                        ApiResponse.error(
                            msg = t.message.toString(),
                            mutableListOf()
                        )
                    )
                    EspressoIdlingResource.decrement()
                }

            })
        return sportsResponse
    }
    fun getTechnologyNews(): LiveData<ApiResponse<List<ArticlesItem>>> {
        EspressoIdlingResource.increment()
        val techResponse = MutableLiveData<ApiResponse<List<ArticlesItem>>>()
        ApiConfig.getApiServeice().getCategoryNews("technology")
            .enqueue(object : Callback<ResponseCatalog<ArticlesItem>>{
                override fun onResponse(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    response: Response<ResponseCatalog<ArticlesItem>>
                ) {
                    if(response.isSuccessful){
                        techResponse.postValue(ApiResponse.success(response.body()?.articles as List<ArticlesItem>))
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<ResponseCatalog<ArticlesItem>>,
                    t: Throwable
                ) {
                    Log.e("debug", "onFailure: ${t.message}" )
                    techResponse.postValue(
                        ApiResponse.error(
                            msg = t.message.toString(),
                            mutableListOf()
                        )
                    )
                    EspressoIdlingResource.decrement()
                }

            })
        return techResponse
    }
}