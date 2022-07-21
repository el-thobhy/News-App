package com.elthobhy.newsapp.data.source.local

import androidx.lifecycle.LiveData
import com.elthobhy.newsapp.data.source.local.entity.*
import com.elthobhy.newsapp.data.source.local.room.ArticleDao

class LocalData private constructor(private val articleDao: ArticleDao){
    companion object{
        private var INSTANCE: LocalData? = null

        fun getInstace(articleDao: ArticleDao): LocalData =
            INSTANCE ?: LocalData(articleDao).apply {
                INSTANCE = this
            }
    }
    fun getAllArticleHeadline(): LiveData<List<ArticleHeadline>> = articleDao.getArticlesHeadline()
    fun insertArticlesHeadline(article: List<ArticleHeadline>) = articleDao.insertArticleHeadline(article)

    fun getAllArticleGeneral(): LiveData<List<ArticleGeneral>> = articleDao.getArticlesGeneral()
    fun insertArticlesGeneral(article: List<ArticleGeneral>) = articleDao.insertArticleGeneral(article)

    fun getAllArticleBusiness(): LiveData<List<ArticleBusiness>> = articleDao.getArticlesBusiness()
    fun insertArticlesBusiness(article: List<ArticleBusiness>) = articleDao.insertArticleBusiness(article)

    fun getAllArticleEntertainment(): LiveData<List<ArticleEntertainment>> = articleDao.getArticlesEntertainment()
    fun insertArticlesEntertainment(article: List<ArticleEntertainment>) = articleDao.insertArticleEntertainment(article)

    fun getAllArticleScience(): LiveData<List<ArticleScience>> = articleDao.getArticlesScience()
    fun insertArticlesScience(article: List<ArticleScience>) = articleDao.insertArticleScience(article)

    fun getAllArticleSports(): LiveData<List<ArticleSports>> = articleDao.getArticlesSports()
    fun insertArticlesSports(article: List<ArticleSports>) = articleDao.insertArticleSports(article)

    fun getAllArticleTechnology(): LiveData<List<ArticleTechnology>> = articleDao.getArticlesTechnology()
    fun insertArticlesTechnology(article: List<ArticleTechnology>) = articleDao.insertArticleTechnology(article)

    fun getAllArticleDetik(): LiveData<List<ArticleDetik>> = articleDao.getArticlesDetik()
    fun insertArticlesDetik(article: List<ArticleDetik>) = articleDao.insertArticleDetik(article)

    fun getAllArticleSuara(): LiveData<List<ArticleSuara>> = articleDao.getArticlesSuara()
    fun insertArticlesSuara(article: List<ArticleSuara>) = articleDao.insertArticleSuara(article)

    fun getAllArticleKapanlagi(): LiveData<List<ArticleKapanlagi>> = articleDao.getArticlesKapanlagi()
    fun insertArticlesKapanlagi(article: List<ArticleKapanlagi>) = articleDao.insertArticleKapanlagi(article)

    fun getAllArticleViva(): LiveData<List<ArticleViva>> = articleDao.getArticlesViva()
    fun insertArticlesViva(article: List<ArticleViva>) = articleDao.insertArticleViva(article)

    fun getAllArticleHealth(): LiveData<List<ArticleHealth>> = articleDao.getArticlesHealth()
    fun insertArticlesHealth(article: List<ArticleHealth>) = articleDao.insertArticleHealth(article)

}