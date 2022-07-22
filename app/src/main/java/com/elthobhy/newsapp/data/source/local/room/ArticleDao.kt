package com.elthobhy.newsapp.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.elthobhy.newsapp.data.source.local.entity.*
import com.elthobhy.newsapp.data.source.local.entity.business.ArticleBusiness
import com.elthobhy.newsapp.data.source.local.entity.detik.ArticleDetik
import com.elthobhy.newsapp.data.source.local.entity.entertainment.ArticleEntertainment
import com.elthobhy.newsapp.data.source.local.entity.general.ArticleGeneral
import com.elthobhy.newsapp.data.source.local.entity.headline.ArticleHeadline
import com.elthobhy.newsapp.data.source.local.entity.health.ArticleHealth
import com.elthobhy.newsapp.data.source.local.entity.kapanlagi.ArticleKapanlagi
import com.elthobhy.newsapp.data.source.local.entity.science.ArticleScience
import com.elthobhy.newsapp.data.source.local.entity.sports.ArticleSports
import com.elthobhy.newsapp.data.source.local.entity.suara.ArticleSuara
import com.elthobhy.newsapp.data.source.local.entity.technology.ArticleTechnology
import com.elthobhy.newsapp.data.source.local.entity.viva.ArticleViva

@Dao
interface ArticleDao {
    @Update
    fun updateArticleHeadline(article: ArticleHeadline)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticleHeadline(article: List<ArticleHeadline>)

    @Query("SELECT * FROM article_entities")
    fun getArticlesHeadline(): LiveData<List<ArticleHeadline>>

    @Query("SELECT * FROM article_entities WHERE bookmarked = 1")
    fun getBookmarkedHeadline(): LiveData<List<ArticleHeadline>>



    @Update
    fun updateArticleBusiness(article: ArticleBusiness)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticleBusiness(article: List<ArticleBusiness>)

    @Query("SELECT * FROM business_entities")
    fun getArticlesBusiness(): LiveData<List<ArticleBusiness>>

    @Query("SELECT * FROM business_entities WHERE bookmarked = 1")
    fun getBookmarkedBusiness(): LiveData<List<ArticleBusiness>>



    @Update
    fun updateArticleEntertainment(article: ArticleEntertainment)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticleEntertainment(article: List<ArticleEntertainment>)

    @Query("SELECT * FROM entertainment_entities")
    fun getArticlesEntertainment(): LiveData<List<ArticleEntertainment>>

    @Query("SELECT * FROM entertainment_entities WHERE bookmarked = 1")
    fun getBookmarkedEntertainment(): LiveData<List<ArticleEntertainment>>



    @Update
    fun updateArticleGeneral(article: ArticleGeneral)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticleGeneral(article: List<ArticleGeneral>)

    @Query("SELECT * FROM general_entities")
    fun getArticlesGeneral(): LiveData<List<ArticleGeneral>>

    @Query("SELECT * FROM general_entities WHERE bookmarked = 1")
    fun getBookmarkedGeneral(): LiveData<List<ArticleGeneral>>



    @Update
    fun updateArticleHealth(article: ArticleHealth)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticleHealth(article: List<ArticleHealth>)

    @Query("SELECT * FROM health_entities")
    fun getArticlesHealth(): LiveData<List<ArticleHealth>>

    @Query("SELECT * FROM health_entities WHERE bookmarked = 1")
    fun getBookmarkedHealth(): LiveData<List<ArticleHealth>>



    @Update
    fun updateArticleScience(article: ArticleScience)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticleScience(article: List<ArticleScience>)

    @Query("SELECT * FROM science_entities")
    fun getArticlesScience(): LiveData<List<ArticleScience>>

    @Query("SELECT * FROM science_entities WHERE bookmarked = 1")
    fun getBookmarkedScience(): LiveData<List<ArticleScience>>



    @Update
    fun updateArticleSports(article: ArticleSports)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticleSports(article: List<ArticleSports>)

    @Query("SELECT * FROM sports_entities")
    fun getArticlesSports(): LiveData<List<ArticleSports>>

    @Query("SELECT * FROM sports_entities WHERE bookmarked = 1")
    fun getBookmarkedSports(): LiveData<List<ArticleSports>>



    @Update
    fun updateArticleTechnology(article: ArticleTechnology)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticleTechnology(article: List<ArticleTechnology>)

    @Query("SELECT * FROM technology_entities")
    fun getArticlesTechnology(): LiveData<List<ArticleTechnology>>

    @Query("SELECT * FROM technology_entities WHERE bookmarked = 1")
    fun getBookmarkedTechnology(): LiveData<List<ArticleTechnology>>



    @Update
    fun updateArticleDetik(article: ArticleDetik)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticleDetik(article: List<ArticleDetik>)

    @Query("SELECT * FROM detik_entities")
    fun getArticlesDetik(): LiveData<List<ArticleDetik>>

    @Query("SELECT * FROM detik_entities WHERE bookmarked = 1")
    fun getBookmarkedDetik(): LiveData<List<ArticleDetik>>



    @Update
    fun updateArticleViva(article: ArticleViva)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticleViva(article: List<ArticleViva>)

    @Query("SELECT * FROM viva_entities")
    fun getArticlesViva(): LiveData<List<ArticleViva>>

    @Query("SELECT * FROM viva_entities WHERE bookmarked = 1")
    fun getBookmarkedViva(): LiveData<List<ArticleViva>>



    @Update
    fun updateArticleKapanlagi(article: ArticleKapanlagi)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticleKapanlagi(article: List<ArticleKapanlagi>)

    @Query("SELECT * FROM kapanlagi_entities")
    fun getArticlesKapanlagi(): LiveData<List<ArticleKapanlagi>>

    @Query("SELECT * FROM kapanlagi_entities WHERE bookmarked = 1")
    fun getBookmarkedKapanlagi(): LiveData<List<ArticleKapanlagi>>



    @Update
    fun updateArticleSuara(article: ArticleSuara)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticleSuara(article: List<ArticleSuara>)

    @Query("SELECT * FROM suara_entities")
    fun getArticlesSuara(): LiveData<List<ArticleSuara>>

    @Query("SELECT * FROM suara_entities WHERE bookmarked = 1")
    fun getBookmarkedSuara(): LiveData<List<ArticleSuara>>




    @Query("SELECT * FROM article_entities WHERE content = :content")
    fun getDetailHeadline(content: String): LiveData<ArticleHeadline>

    @Query("SELECT * FROM detik_entities WHERE content = :content")
    fun getDetailDetik(content: String): LiveData<ArticleDetik>

    @Query("SELECT * FROM viva_entities WHERE content = :content")
    fun getDetailViva(content: String): LiveData<ArticleViva>

    @Query("SELECT * FROM suara_entities WHERE content = :content")
    fun getDetailSuara(content: String): LiveData<ArticleSuara>

    @Query("SELECT * FROM business_entities WHERE content = :content")
    fun getDetailBusiness(content: String): LiveData<ArticleBusiness>

    @Query("SELECT * FROM entertainment_entities WHERE content = :content")
    fun getDetailEntertainment(content: String): LiveData<ArticleEntertainment>

    @Query("SELECT * FROM general_entities WHERE content = :content")
    fun getDetailGeneral(content: String): LiveData<ArticleGeneral>

    @Query("SELECT * FROM health_entities WHERE content = :content")
    fun getDetailHealth(content: String): LiveData<ArticleHealth>

    @Query("SELECT * FROM science_entities WHERE content = :content")
    fun getDetailScience(content: String): LiveData<ArticleScience>

    @Query("SELECT * FROM sports_entities WHERE content = :content")
    fun getDetailSports(content: String): LiveData<ArticleSports>

    @Query("SELECT * FROM technology_entities WHERE content = :content")
    fun getDetailTechnology(content: String): LiveData<ArticleTechnology>

    @Query("SELECT * FROM kapanlagi_entities WHERE content = :content")
    fun getDetailKapanlagi(content: String): LiveData<ArticleKapanlagi>

}