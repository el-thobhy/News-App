package com.elthobhy.newsapp.data.source

import androidx.lifecycle.LiveData
import com.elthobhy.newsapp.data.source.local.entity.*
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
}