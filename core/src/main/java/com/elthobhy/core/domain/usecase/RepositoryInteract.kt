package com.elthobhy.core.domain.usecase

import androidx.lifecycle.LiveData
import com.elthobhy.core.domain.model.Domain
import com.elthobhy.core.domain.model.User
import com.elthobhy.core.domain.repository.CatalogNewsDataSource
import com.elthobhy.core.utils.vo.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

class RepositoryInteract(private val catalogNewsDataSource: CatalogNewsDataSource) : UseCase {
    override fun getHeadline(): Flow<Resource<List<Domain>>> =
        catalogNewsDataSource.getTopHeadlines()

    override fun getDataLogin(email: String, password: String): LiveData<Resource<AuthResult>> =
        catalogNewsDataSource.getDataLogin(email, password)

    override fun loginWithGoogle(
        name: String,
        email: String,
        credential: AuthCredential
    ): LiveData<Resource<AuthResult>> =
        catalogNewsDataSource.loginWithGoogle(name,email,credential)

    override fun getIndonesiaNews(
        news: String,
        detik: Boolean,
        suara: Boolean,
        kapanlagi: Boolean,
        liputan: Boolean
    ): Flow<Resource<List<Domain>>> =
        catalogNewsDataSource.getIndonesiaNews(news, detik, suara, kapanlagi, liputan)

    override fun getSearch(q: String): Flow<List<Domain>> = catalogNewsDataSource.getSearch(q)
    override fun getDetail(content: String): LiveData<Domain> =
        catalogNewsDataSource.getDetailTopHeadlines(content)

    override fun setFavoriteHeadline(article: Domain, newState: Boolean) {
        catalogNewsDataSource.setBookmark(article,newState)
    }

    override fun getFavorite(): Flow<List<Domain>> {
        return catalogNewsDataSource.getFavorite()
    }
    override fun getDataRegister(name: String, email: String, password: String): LiveData<Resource<AuthResult>> =
        catalogNewsDataSource.getDataRegister(name, email, password)

    override fun forgotPassword(email: String): LiveData<Resource<Void>> =
        catalogNewsDataSource.forgotPassword(email)

    override fun changePassword(
        newPass: String,
        credential: AuthCredential
    ): LiveData<Resource<Void>> = catalogNewsDataSource.changePassword(newPass, credential)

    override fun getDataUser(uid: String): LiveData<Resource<User>> =
        catalogNewsDataSource.getDataUser(uid)

}