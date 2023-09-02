package com.elthobhy.core.domain.repository

import androidx.lifecycle.LiveData
import com.elthobhy.core.domain.model.Domain
import com.elthobhy.core.domain.model.User
import com.elthobhy.core.utils.vo.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface CatalogNewsDataSource {
    fun getTopHeadlines(): Flow<Resource<List<Domain>>>
    fun getSearch(q: String): Flow<List<Domain>>
    fun getIndonesiaNews(
        news: String,
        detik: Boolean,
        suara: Boolean,
        kapanlagi: Boolean,
        liputan: Boolean
    ): Flow<Resource<List<Domain>>>

    fun getDetailTopHeadlines(content: String): LiveData<Domain>
    fun setBookmark(article: Domain, state: Boolean)
    fun getFavorite(): Flow<List<Domain>>
    fun getDataLogin(email: String, password: String): LiveData<Resource<AuthResult>>
    fun loginWithGoogle(name: String, email: String, credential: AuthCredential): LiveData<Resource<AuthResult>>
    fun getDataRegister(name: String, email: String, password: String): LiveData<Resource<AuthResult>>
    fun forgotPassword(email: String): LiveData<Resource<Void>>
    fun changePassword(newPass: String, credential: AuthCredential): LiveData<Resource<Void>>
    fun getDataUser(uid: String): LiveData<Resource<User>>
}