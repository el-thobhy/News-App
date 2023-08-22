package com.elthobhy.newsapp.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.elthobhy.newsapp.data.source.remote.response.vo.ApiResponse
import com.elthobhy.newsapp.data.source.remote.response.vo.StatusResponseNetwork
import com.elthobhy.newsapp.utils.AppExecutors
import com.elthobhy.newsapp.utils.vo.Resource

abstract class NetworkBoundResource<ResultType, RequestType>(private val executors: AppExecutors) {
    private val  result = MediatorLiveData<Resource<ResultType>>()
    init{
//        result.value = Resource.loading(null)

        val dbSource = loadFromDB()

        result.addSource(dbSource){data->
            result.removeSource(dbSource)
            if(shouldFetch(data)){
                fetchFromNetwork(dbSource)
            }else{
                result.addSource(dbSource){newData->
                    result.value = Resource.success(newData)
                }
            }
        }
    }

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>){
        val apiResponse = createCall()
        result.addSource(dbSource){newData->
            result.value = Resource.loading(newData)
        }
        result.addSource(apiResponse){response->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when(response.statusNetwork){
                StatusResponseNetwork.SUCCESS ->
                    executors.diskIO().execute{
                        saveCallResult(response.body)
                        executors.mainThread().execute{
                            result.addSource(loadFromDB()){newData->
                                result.value = Resource.success(newData)
                                Log.e("NBR", "fetchFromNetwork: ${result.value}" )
                            }
                        }
                    }
                StatusResponseNetwork.EMPTY ->{
                    result.addSource(loadFromDB()){newData->
                        result.value = Resource.success(newData)
                    }
                }
                StatusResponseNetwork.ERROR->{
                    onFetchFailed()
                    result.addSource(dbSource){newData->
                        result.value = Resource.error(response.message,newData)
                    }
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    abstract fun saveCallResult(data: RequestType?)

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun loadFromDB(): LiveData<ResultType>

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}