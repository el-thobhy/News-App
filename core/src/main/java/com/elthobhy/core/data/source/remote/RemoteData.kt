package com.elthobhy.core.data.source.remote

import com.elthobhy.core.data.source.remote.network.ApiConfig
import com.elthobhy.core.data.source.remote.response.ArticlesItem
import com.elthobhy.core.data.source.remote.response.vo.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteData {

    suspend fun getTopHeadlines(): Flow<ApiResponse<List<ArticlesItem>>>{
        return flow{
            try {
                val response = ApiConfig.getApiServeice().getTopHeadlines("indonesia")
                val list = response.articles
                if(list?.isNotEmpty()==true){
                    emit(ApiResponse.Success(list))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
    suspend fun getSearch(q: String): Flow<ApiResponse<List<ArticlesItem>>>{
        return flow{
            try {
                val response = ApiConfig.getApiServeice().getSearch(q)
                val list = response.articles
                if(list?.isNotEmpty()==true){
                    emit(ApiResponse.Success(list))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}