package com.elthobhy.newsapp.data.source.local

import androidx.lifecycle.LiveData
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

    fun setFavoriteHeadline(article: ArticleHeadline, newState: Boolean) {
        article.bookmarked = newState
        articleDao.updateArticleHeadline(article)
    }
    fun setFavoriteBusiness(article: ArticleBusiness, newState: Boolean) {
        article.bookmarked = newState
        articleDao.updateArticleBusiness(article)
    }
    fun setFavoriteEntertainment(article: ArticleEntertainment, newState: Boolean) {
        article.bookmarked = newState
        articleDao.updateArticleEntertainment(article)
    }
    fun setFavoriteGeneral(article: ArticleGeneral, newState: Boolean) {
        article.bookmarked = newState
        articleDao.updateArticleGeneral(article)
    }
    fun setFavoriteHealth(article: ArticleHealth, newState: Boolean) {
        article.bookmarked = newState
        articleDao.updateArticleHealth(article)
    }
    fun setFavoriteScience(article: ArticleScience, newState: Boolean) {
        article.bookmarked = newState
        articleDao.updateArticleScience(article)
    }
    fun setFavoriteSports(article: ArticleSports, newState: Boolean) {
        article.bookmarked = newState
        articleDao.updateArticleSports(article)
    }
    fun setFavoriteTechnology(article: ArticleTechnology, newState: Boolean) {
        article.bookmarked = newState
        articleDao.updateArticleTechnology(article)
    }
    fun setFavoriteDetik(article: ArticleDetik, newState: Boolean) {
        article.bookmarked = newState
        articleDao.updateArticleDetik(article)
    }
    fun setFavoriteViva(article: ArticleViva, newState: Boolean) {
        article.bookmarked = newState
        articleDao.updateArticleViva(article)
    }
    fun setFavoriteKapanlagi(article: ArticleKapanlagi, newState: Boolean) {
        article.bookmarked = newState
        articleDao.updateArticleKapanlagi(article)
    }
    fun setFavoriteSuara(article: ArticleSuara, newState: Boolean) {
        article.bookmarked = newState
        articleDao.updateArticleSuara(article)
    }

    fun getAllFavoritesBusiness(): LiveData<List<ArticleBusiness>> = articleDao.getBookmarkedBusiness()
    fun getAllFavoritesEntertainment(): LiveData<List<ArticleEntertainment>> = articleDao.getBookmarkedEntertainment()
    fun getAllFavoritesGeneral(): LiveData<List<ArticleGeneral>> = articleDao.getBookmarkedGeneral()
    fun getAllFavoritesHealth(): LiveData<List<ArticleHealth>> = articleDao.getBookmarkedHealth()
    fun getAllFavoritesScience(): LiveData<List<ArticleScience>> = articleDao.getBookmarkedScience()
    fun getAllFavoritesSports(): LiveData<List<ArticleSports>> = articleDao.getBookmarkedSports()
    fun getAllFavoritesTechnology(): LiveData<List<ArticleTechnology>> = articleDao.getBookmarkedTechnology()
    fun getAllFavoritesHeadline(): LiveData<List<ArticleHeadline>> = articleDao.getBookmarkedHeadline()
    fun getAllFavoritesDetik(): LiveData<List<ArticleDetik>> = articleDao.getBookmarkedDetik()
    fun getAllFavoritesViva(): LiveData<List<ArticleViva>> = articleDao.getBookmarkedViva()
    fun getAllFavoritesKapanlagi(): LiveData<List<ArticleKapanlagi>> = articleDao.getBookmarkedKapanlagi()
    fun getAllFavoritesSuara(): LiveData<List<ArticleSuara>> = articleDao.getBookmarkedSuara()
    
    
    fun getDetailHeadline(content: String): LiveData<ArticleHeadline> = articleDao.getDetailHeadline(content)

    fun getDetailDetik(content: String): LiveData<ArticleDetik> = articleDao.getDetailDetik(content)

    fun getDetailViva(content: String): LiveData<ArticleViva> = articleDao.getDetailViva(content)

    fun getDetailSuara(content: String): LiveData<ArticleSuara> = articleDao.getDetailSuara(content)

    fun getDetailBusiness(content: String): LiveData<ArticleBusiness> = articleDao.getDetailBusiness(content)

    fun getDetailEntertainment(content: String): LiveData<ArticleEntertainment> = articleDao.getDetailEntertainment(content)

    fun getDetailGeneral(content: String): LiveData<ArticleGeneral> = articleDao.getDetailGeneral(content)

    fun getDetailHealth(content: String): LiveData<ArticleHealth> = articleDao.getDetailHealth(content)

    fun getDetailScience(content: String): LiveData<ArticleScience> = articleDao.getDetailScience(content)

    fun getDetailSports(content: String): LiveData<ArticleSports> = articleDao.getDetailSports(content)

    fun getDetailTechnology(content: String): LiveData<ArticleTechnology> = articleDao.getDetailTechnology(content)

    fun getDetailKapanlagi(content: String): LiveData<ArticleKapanlagi> = articleDao.getDetailKapanlagi(content)
}