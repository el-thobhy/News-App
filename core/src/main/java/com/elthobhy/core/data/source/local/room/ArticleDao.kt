package com.elthobhy.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.elthobhy.core.data.source.local.entity.headline.ArticleHeadlineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Update
    fun updateArticleHeadline(article: ArticleHeadlineEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticleHeadline(article: List<ArticleHeadlineEntity>)

    @Query("SELECT * FROM article_entities WHERE title LIKE '%' || :q || '%'")
    fun getSearch(q: String): Flow<List<ArticleHeadlineEntity>>

    @Query("SELECT * FROM article_entities")
    fun getArticlesHeadline(): Flow<List<ArticleHeadlineEntity>>

    @Query("SELECT * FROM article_entities WHERE bookmarked = 1")
    fun getBookmarkedHeadline(): LiveData<List<ArticleHeadlineEntity>>

    @Query("SELECT * FROM article_entities WHERE title = :title")
    fun getDetailHeadline(title: String): LiveData<ArticleHeadlineEntity>

}