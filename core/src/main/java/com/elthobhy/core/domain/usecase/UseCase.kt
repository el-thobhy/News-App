package com.elthobhy.core.domain.usecase

import androidx.lifecycle.LiveData
import com.elthobhy.core.domain.model.Domain
import com.elthobhy.core.utils.vo.Resource
import kotlinx.coroutines.flow.Flow

interface UseCase {
    fun getHeadline(): Flow<Resource<List<Domain>>>
    fun getIndonesiaNews(
        news: String,
        detik: Boolean,
        suara: Boolean,
        kapanlagi: Boolean,
        liputan: Boolean
    ): Flow<Resource<List<Domain>>>

    fun getSearch(q: String): Flow<List<Domain>>
    fun getDetail(content: String): LiveData<Domain>
    fun setFavoriteHeadline(article: Domain, newState: Boolean)
    fun getFavorite(): Flow<List<Domain>>
}