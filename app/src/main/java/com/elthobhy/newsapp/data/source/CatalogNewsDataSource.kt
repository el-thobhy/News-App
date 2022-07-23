package com.elthobhy.newsapp.data.source

import androidx.lifecycle.LiveData
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
import com.elthobhy.newsapp.utils.vo.Resource

interface CatalogNewsDataSource {
    fun getTopHeadlines(): LiveData<Resource<List<ArticleHeadline>>>
    fun getDetikNews(): LiveData<Resource<List<ArticleDetik>>>
    fun getVivaNews(): LiveData<Resource<List<ArticleViva>>>
    fun getKapanlagiNews(): LiveData<Resource<List<ArticleKapanlagi>>>
    fun getSuaraNews(): LiveData<Resource<List<ArticleSuara>>>
    fun getBusinessNews(): LiveData<Resource<List<ArticleBusiness>>>
    fun getEntertainmentNews(): LiveData<Resource<List<ArticleEntertainment>>>
    fun getGeneralNews(): LiveData<Resource<List<ArticleGeneral>>>
    fun getHealthNews(): LiveData<Resource<List<ArticleHealth>>>
    fun getScienceNews(): LiveData<Resource<List<ArticleScience>>>
    fun getSportsNews(): LiveData<Resource<List<ArticleSports>>>
    fun getTechnologyNews(): LiveData<Resource<List<ArticleTechnology>>>

    fun setFavoriteTechnology(article: ArticleTechnology, state: Boolean)
    fun getFavoritesTechnology() : LiveData<List<ArticleTechnology>>

    fun setFavoriteSports(article: ArticleSports, state: Boolean)
    fun getFavoritesSports() : LiveData<List<ArticleSports>>

    fun setFavoriteScience(article: ArticleScience, state: Boolean)
    fun getFavoritesScience() : LiveData<List<ArticleScience>>

    fun setFavoriteHealth(article: ArticleHealth, state: Boolean)
    fun getFavoritesHealth() : LiveData<List<ArticleHealth>>

    fun setFavoriteGeneral(article: ArticleGeneral, state: Boolean)
    fun getFavoritesGeneral() : LiveData<List<ArticleGeneral>>

    fun setFavoriteEntertainment(article: ArticleEntertainment, state: Boolean)
    fun getFavoritesEntertainment() : LiveData<List<ArticleEntertainment>>

    fun setFavoriteBusiness(article: ArticleBusiness, state: Boolean)
    fun getFavoritesBusiness() : LiveData<List<ArticleBusiness>>

    fun setFavoriteSuara(article: ArticleSuara, state: Boolean)
    fun getFavoritesSuara() : LiveData<List<ArticleSuara>>

    fun setFavoriteKapanlagi(article: ArticleKapanlagi, state: Boolean)
    fun getFavoritesKapanlagi() : LiveData<List<ArticleKapanlagi>>

    fun setFavoriteViva(article: ArticleViva, state: Boolean)
    fun getFavoritesViva() : LiveData<List<ArticleViva>>

    fun setFavoriteDetik(article: ArticleDetik, state: Boolean)
    fun getFavoritesDetik() : LiveData<List<ArticleDetik>>

    fun setFavoriteHeadline(article: ArticleHeadline, state: Boolean)
    fun getFavoritesHeadline() : LiveData<List<ArticleHeadline>>

    fun getDetailTopHeadlines(content: String): LiveData<ArticleHeadline>
    fun getDetailDetikNews(content: String): LiveData<ArticleDetik>
    fun getDetailVivaNews(content: String): LiveData<ArticleViva>
    fun getDetailKapanlagiNews(content: String): LiveData<ArticleKapanlagi>
    fun getDetailSuaraNews(content: String): LiveData<ArticleSuara>
    fun getDetailBusinessNews(content: String): LiveData<ArticleBusiness>
    fun getDetailEntertainmentNews(content: String): LiveData<ArticleEntertainment>
    fun getDetailGeneralNews(content: String): LiveData<ArticleGeneral>
    fun getDetailHealthNews(content: String): LiveData<ArticleHealth>
    fun getDetailScienceNews(content: String): LiveData<ArticleScience>
    fun getDetailSportsNews(content: String): LiveData<ArticleSports>
    fun getDetailTechnologyNews(content: String): LiveData<ArticleTechnology>
}