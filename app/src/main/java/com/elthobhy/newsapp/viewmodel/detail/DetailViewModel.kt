package com.elthobhy.newsapp.viewmodel.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
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

class DetailViewModel(private val catalogRepo: CatalogNewsRepository) :
    ViewModel() {
    fun setBookmarkedViva(article: ArticleViva) {
        val newState = !article.bookmarked
        catalogRepo.setFavoriteViva(article, newState)
    }

    fun setBookmarkedTechnology(article: ArticleTechnology) {
        val newState = !article.bookmarked
        catalogRepo.setFavoriteTechnology(article, newState)
    }
    fun setBookmarkedDetik(article: ArticleDetik) {
        val newState = !article.bookmarked
        catalogRepo.setFavoriteDetik(article, newState)
    }

    fun setBookmarkedSuara(article: ArticleSuara) {
        val newState = !article.bookmarked
        catalogRepo.setFavoriteSuara(article, newState)
    }
    fun setBookmarkedKapanlagi(article: ArticleKapanlagi) {
        val newState = !article.bookmarked
        catalogRepo.setFavoriteKapanlagi(article, newState)
    }

    fun setBookmarkedBusiness(article: ArticleBusiness) {
        val newState = !article.bookmarked
        catalogRepo.setFavoriteBusiness(article, newState)
    }
    fun setBookmarkedEntertainment(article: ArticleEntertainment) {
        val newState = !article.bookmarked
        catalogRepo.setFavoriteEntertainment(article, newState)
    }

    fun setBookmarkedGeneral(article: ArticleGeneral) {
        val newState = !article.bookmarked
        catalogRepo.setFavoriteGeneral(article, newState)
    }
    fun setBookmarkedScience(article: ArticleScience) {
        val newState = !article.bookmarked
        catalogRepo.setFavoriteScience(article, newState)
    }

    fun setBookmarkedHealth(article: ArticleHealth) {
        val newState = !article.bookmarked
        catalogRepo.setFavoriteHealth(article, newState)
    }
    fun setBookmarkedSports(article: ArticleSports) {
        val newState = !article.bookmarked
        catalogRepo.setFavoriteSports(article, newState)
    }

    fun setBookmarkedHeadline(article: ArticleHeadline) {
        val newState = !article.bookmarked
        catalogRepo.setFavoriteHeadline(article, newState)
    }



    fun getBusinessNews(content: String): LiveData<ArticleBusiness> =
        catalogRepo.getDetailBusinessNews(content)
    fun getDetikNews(content: String): LiveData<ArticleDetik> =
        catalogRepo.getDetailDetikNews(content)
    fun getEntertainment(content: String): LiveData<ArticleEntertainment> =
        catalogRepo.getDetailEntertainmentNews(content)
    fun getGeneralNews(content: String): LiveData<ArticleGeneral> =
        catalogRepo.getDetailGeneralNews(content)
    fun getHeadline(content: String): LiveData<ArticleHeadline> =
        catalogRepo.getDetailTopHeadlines(content)
    fun getHealthNews(content: String): LiveData<ArticleHealth> =
        catalogRepo.getDetailHealthNews(content)
    fun getKapanlagiNews(content: String): LiveData<ArticleKapanlagi> =
        catalogRepo.getDetailKapanlagiNews(content)
    fun getScienceNews(content: String): LiveData<ArticleScience> =
        catalogRepo.getDetailScienceNews(content)
    fun getSportsNews(content: String): LiveData<ArticleSports> =
        catalogRepo.getDetailSportsNews(content)
    fun getSuaraNews(content: String): LiveData<ArticleSuara> =
        catalogRepo.getDetailSuaraNews(content)
    fun getTechnologyNews(content: String): LiveData<ArticleTechnology> =
        catalogRepo.getDetailTechnologyNews(content)
    fun getVivaNews(content: String): LiveData<ArticleViva> =
        catalogRepo.getDetailVivaNews(content)
}