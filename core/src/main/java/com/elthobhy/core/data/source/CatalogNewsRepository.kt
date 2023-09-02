package com.elthobhy.core.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.elthobhy.core.data.source.local.LocalData
import com.elthobhy.core.data.source.remote.RemoteData
import com.elthobhy.core.data.source.remote.response.ArticlesItem
import com.elthobhy.core.data.source.remote.response.vo.ApiResponse
import com.elthobhy.core.domain.model.Domain
import com.elthobhy.core.domain.model.User
import com.elthobhy.core.domain.repository.CatalogNewsDataSource
import com.elthobhy.core.utils.AppExecutors
import com.elthobhy.core.utils.DataMapper
import com.elthobhy.core.utils.vo.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CatalogNewsRepository(
    private val remoteData: RemoteData,
    private val localData: LocalData,
    private val appExecutors: AppExecutors
) : CatalogNewsDataSource {

    override fun getTopHeadlines(): Flow<Resource<List<Domain>>> {
        return object : com.elthobhy.core.data.source.NetworkBoundResource<List<Domain>, List<ArticlesItem>>(appExecutors) {
            override fun shouldFetch(data: List<Domain>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val dataMap = DataMapper.mapResponseToEntity(data,
                    detik = false,
                    suara = false,
                    kapanlagi = false,
                    liputan = false
                )
                localData.insertArticlesHeadline(dataMap)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteData.getTopHeadlines()

            override fun loadFromDB(): Flow<List<Domain>> =
                localData.getAllArticleHeadline().map { DataMapper.mapEntityToDomain(it) }

        }.asFlow()
    }

    override fun getIndonesiaNews(
        news: String,
        detik: Boolean,
        suara: Boolean,
        kapanlagi: Boolean,
        liputan: Boolean,
    ): Flow<Resource<List<Domain>>> {
        return object : com.elthobhy.core.data.source.NetworkBoundResource<List<Domain>, List<ArticlesItem>>(appExecutors) {
            override fun shouldFetch(data: List<Domain>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val dataMap = DataMapper.mapResponseToEntity(data, detik, suara, kapanlagi, liputan)
                localData.insertArticlesHeadline(dataMap)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteData.getIndonesianNews(news)

            override fun loadFromDB(): Flow<List<Domain>> {
                return when{
                    detik -> localData.getDetikNews().map { DataMapper.mapEntityToDomain(it) }
                    suara -> localData.getSuaraNews().map { DataMapper.mapEntityToDomain(it) }
                    kapanlagi -> localData.getKapanlagiNews().map { DataMapper.mapEntityToDomain(it) }
                    liputan -> localData.getLiputanNews().map { DataMapper.mapEntityToDomain(it) }
                    else -> localData.getAllIndonesiaNews().map { DataMapper.mapEntityToDomain(it) }
                }
            }


        }.asFlow()
    }


    override fun getSearch(q: String): Flow<List<Domain>> {
        return localData.getSearch(q).map { DataMapper.mapEntityToDomain(it) }
    }


    override fun getDetailTopHeadlines(content: String): LiveData<Domain> =
        localData.getDetailHeadline(content).map { DataMapper.itemEntityToDomain(it) }

    override fun setBookmark(article: Domain, state: Boolean) {
        val data = DataMapper.domainToEntity(article)
        appExecutors.diskIO().execute{localData.setFavoriteHeadline(data,state)}
    }

    override fun getFavorite(): Flow<List<Domain>> {
        return localData.getAllFavoritesHeadline().map { DataMapper.mapEntityToDomain(it) }
    }

    override fun getDataLogin(email: String, password: String): LiveData<Resource<AuthResult>> {
        return remoteData.login(email, password)
    }

    override fun loginWithGoogle(
        name: String,
        email: String,
        credential: AuthCredential
    ): LiveData<Resource<AuthResult>> {
        return remoteData.loginWithGoogle(name, email, credential)
    }
    override fun getDataRegister(name: String, email: String, password: String): LiveData<Resource<AuthResult>> {
        return remoteData.register(name, email, password)
    }
    override fun forgotPassword(email: String): LiveData<Resource<Void>> {
        return remoteData.forgotPassword(email)
    }
    override fun changePassword(
        newPass: String,
        credential: AuthCredential
    ): LiveData<Resource<Void>> {
        return remoteData.changePassword(newPass, credential)
    }

    override fun getDataUser(uid: String): LiveData<Resource<User>> {
        return remoteData.getDataUser(uid)
    }

}
