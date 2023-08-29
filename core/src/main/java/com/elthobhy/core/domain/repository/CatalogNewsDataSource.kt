package com.elthobhy.core.domain.repository

import androidx.lifecycle.LiveData
import com.elthobhy.core.domain.model.Domain
import com.elthobhy.core.utils.vo.Resource
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
}