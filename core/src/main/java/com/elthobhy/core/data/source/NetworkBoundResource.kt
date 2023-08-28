package com.elthobhy.core.data.source

import com.elthobhy.core.data.source.remote.response.vo.ApiResponse
import com.elthobhy.core.utils.AppExecutors
import com.elthobhy.core.utils.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType>(private val executors: AppExecutors) {
    private val  result : Flow<Resource<ResultType>> = flow {
        emit(Resource.loading())
        val dbSource = loadFromDB().first()
        if(shouldFetch(dbSource)){
            emit(Resource.loading())
            when (val apiResponse = createCall().first()){
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map {
                        Resource.success(it)
                    })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map {
                        Resource.success(it)
                    })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.error(apiResponse.errorMessage))
                }
            }
        }else{
            emitAll((loadFromDB().map {
                Resource.success(it)
            }))
        }

    }


    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected open fun onFetchFailed() {}

    abstract suspend fun saveCallResult(data: RequestType)

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract fun loadFromDB(): Flow<ResultType>

    fun asFlow(): Flow<Resource<ResultType>> = result
}