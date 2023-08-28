package com.elthobhy.core.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.elthobhy.core.data.source.local.LocalData
import com.elthobhy.core.data.source.remote.RemoteData
import com.elthobhy.core.data.source.remote.response.ArticlesItem
import com.elthobhy.core.data.source.remote.response.vo.ApiResponse
import com.elthobhy.core.domain.model.Domain
import com.elthobhy.core.domain.repository.CatalogNewsDataSource
import com.elthobhy.core.utils.AppExecutors
import com.elthobhy.core.utils.DataMapper
import com.elthobhy.core.utils.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CatalogNewsRepository(
    private val remoteData: RemoteData,
    private val localData: LocalData,
    private val appExecutors: AppExecutors
): CatalogNewsDataSource {

    override fun getTopHeadlines(): Flow<Resource<List<Domain>>> {
        return object : NetworkBoundResource<List<Domain>, List<ArticlesItem>>(appExecutors){
            override fun shouldFetch(data: List<Domain>?): Boolean =
                true

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val dataMap = DataMapper.mapResponseToEntity(data)
                localData.insertArticlesHeadline(dataMap)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteData.getTopHeadlines()

            override fun loadFromDB(): Flow<List<Domain>> =
                localData.getAllArticleHeadline().map { DataMapper.mapEntityToDomain(it) }

        }.asFlow()
    }

    override fun getSearch(q: String): Flow<List<Domain>> {
        return localData.getSearch(q).map { DataMapper.mapEntityToDomain(it) }
    }




    override fun getDetailTopHeadlines(content: String): LiveData<Domain> =
        localData.getDetailHeadline(content).map { DataMapper.itemEntityToDomain(it) }
}
