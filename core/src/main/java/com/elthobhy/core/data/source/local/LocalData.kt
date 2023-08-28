package com.elthobhy.core.data.source.local

import androidx.lifecycle.LiveData
import com.elthobhy.core.data.source.local.entity.headline.ArticleHeadlineEntity
import com.elthobhy.core.data.source.local.room.ArticleDao
import kotlinx.coroutines.flow.Flow

class LocalData private constructor(private val articleDao: ArticleDao){

    companion object{
        private var INSTANCE: LocalData? = null

        fun getInstace(articleDao: ArticleDao): LocalData =
            INSTANCE ?: LocalData(articleDao).apply {
                INSTANCE = this
            }
    }
    fun getAllArticleHeadline(): Flow<List<ArticleHeadlineEntity>> = articleDao.getArticlesHeadline()
    suspend fun insertArticlesHeadline(article: List<ArticleHeadlineEntity>) = articleDao.insertArticleHeadline(article)
    fun getSearch(q: String): Flow<List<ArticleHeadlineEntity>> = articleDao.getSearch(q)

    fun setFavoriteHeadline(article: ArticleHeadlineEntity, newState: Boolean) {
        article.bookmarked = newState
        articleDao.updateArticleHeadline(article)
    }

    fun getAllFavoritesHeadline(): LiveData<List<ArticleHeadlineEntity>> = articleDao.getBookmarkedHeadline()
    
    fun getDetailHeadline(content: String): LiveData<ArticleHeadlineEntity> = articleDao.getDetailHeadline(content)
}