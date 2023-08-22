package com.elthobhy.newsapp.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import com.elthobhy.newsapp.data.source.local.LocalData
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
import com.elthobhy.newsapp.data.source.remote.RemoteData
import com.elthobhy.newsapp.data.source.remote.response.ArticlesItem
import com.elthobhy.newsapp.data.source.remote.response.vo.ApiResponse
import com.elthobhy.newsapp.utils.AppExecutors
import com.elthobhy.newsapp.utils.vo.Resource

class CatalogNewsRepository private constructor(
    private val remoteData: RemoteData,
    private val localData: LocalData,
    private val appExecutors: AppExecutors
):
    CatalogNewsDataSource {
        companion object{
            @Volatile
            private var instance : CatalogNewsRepository? = null
            fun getInstance(
                remoteData: RemoteData,
                localData: LocalData,
                appExecutors: AppExecutors
            ): CatalogNewsRepository =
                instance ?: synchronized(this) {
                    instance ?: CatalogNewsRepository(
                        remoteData,
                        localData,
                        appExecutors
                    ).apply {
                        instance = this
                    }
                }
        }

    override fun getTopHeadlines(): LiveData<Resource<List<ArticleHeadline>>> {
        return object : NetworkBoundResource<List<ArticleHeadline>, List<ArticlesItem>>(appExecutors){
            override fun shouldFetch(data: List<ArticleHeadline>?): Boolean =
                true

            override fun saveCallResult(data: List<ArticlesItem>?) {
                val listNews = ArrayList<ArticleHeadline>()
                if (data != null) {
                    for(response in data){
                        val article = response.title?.let {
                            ArticleHeadline(
                                author = response.author,
                                source = Source(name=response.source?.name),
                                title = it,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt,
                                content = response.content,
                                url = response.url
                            )
                        }
                        if (article != null) {
                            listNews.add(article)
                        }
                        Log.e("Cnr", "saveCallResult: $listNews" )
                    }
                }
                localData.insertArticlesHeadline(listNews)
            }

            public override fun createCall(): LiveData<ApiResponse<List<ArticlesItem>>> =
                remoteData.getTopHeadlines()

            public override fun loadFromDB(): LiveData<List<ArticleHeadline>> =
                localData.getAllArticleHeadline()

        }.asLiveData()
    }

    override fun getDetikNews(): LiveData<Resource<List<ArticleDetik>>> {
        return object : NetworkBoundResource<List<ArticleDetik>, List<ArticlesItem>>(appExecutors){
            override fun shouldFetch(data: List<ArticleDetik>?): Boolean =
                true

            override fun saveCallResult(data: List<ArticlesItem>?) {
                val listNews = ArrayList<ArticleDetik>()
                if (data != null) {
                    for(response in data){
                        val article = response.title?.let {
                            ArticleDetik(
                                author = response.author,
                                source = Source(name=response.source?.name),
                                title = it,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt,
                                content = response.content,
                                url = response.url
                            )
                        }
                        if(article!=null){
                            listNews.add(article)
                        }
                    }
                }
                localData.insertArticlesDetik(listNews)
            }

            public override fun createCall(): LiveData<ApiResponse<List<ArticlesItem>>> =
                remoteData.getDetikNews()


            public override fun loadFromDB(): LiveData<List<ArticleDetik>> =
                localData.getAllArticleDetik()

        }.asLiveData()
    }

    override fun getVivaNews(): LiveData<Resource<List<ArticleViva>>> {
        return object : NetworkBoundResource<List<ArticleViva>, List<ArticlesItem>>(appExecutors){
            override fun shouldFetch(data: List<ArticleViva>?): Boolean =
                true

            override fun saveCallResult(data: List<ArticlesItem>?) {
                val listNews = ArrayList<ArticleViva>()
                if (data != null) {
                    for(response in data){
                        val article = response.title?.let {
                            ArticleViva(
                                author = response.author,
                                source = Source(name=response.source?.name),
                                title = it,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt,
                                content = response.content,
                                url = response.url
                            )
                        }
                        if(article!=null){
                            listNews.add(article)
                        }
                    }
                }
                localData.insertArticlesViva(listNews)
            }

            public override fun createCall(): LiveData<ApiResponse<List<ArticlesItem>>> =
                remoteData.getVivaNews()

            public override fun loadFromDB(): LiveData<List<ArticleViva>> =
                localData.getAllArticleViva()

        }.asLiveData()
    }

    override fun getKapanlagiNews(): LiveData<Resource<List<ArticleKapanlagi>>> {
        return object : NetworkBoundResource<List<ArticleKapanlagi>, List<ArticlesItem>>(appExecutors){
            override fun shouldFetch(data: List<ArticleKapanlagi>?): Boolean =
                true

            override fun saveCallResult(data: List<ArticlesItem>?) {
                val listNews = ArrayList<ArticleKapanlagi>()
                if (data != null) {
                    for(response in data){
                        val article = response.title?.let {
                            ArticleKapanlagi(
                                author = response.author,
                                source = Source(name=response.source?.name),
                                title = it,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt,
                                content = response.content,
                                url = response.url
                            )
                        }
                        if(article!=null){
                            listNews.add(article)
                        }
                    }
                }
                localData.insertArticlesKapanlagi(listNews)
            }

            public override fun createCall(): LiveData<ApiResponse<List<ArticlesItem>>> =
                remoteData.getKapanlagiNews()

            public override fun loadFromDB(): LiveData<List<ArticleKapanlagi>> =
                localData.getAllArticleKapanlagi()

        }.asLiveData()
    }

    override fun getSuaraNews(): LiveData<Resource<List<ArticleSuara>>> {
        return object : NetworkBoundResource<List<ArticleSuara>, List<ArticlesItem>>(appExecutors){
            override fun shouldFetch(data: List<ArticleSuara>?): Boolean =
                true

            override fun saveCallResult(data: List<ArticlesItem>?) {
                val listNews = ArrayList<ArticleSuara>()
                if (data != null) {
                    for(response in data){
                        val article = response.title?.let {
                            ArticleSuara(
                                author = response.author,
                                source = Source(name=response.source?.name),
                                title = it,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt,
                                content = response.content,
                                url = response.url
                            )
                        }
                        if(article!=null){
                            listNews.add(article)
                        }
                    }
                }
                localData.insertArticlesSuara(listNews)
            }

            public override fun createCall(): LiveData<ApiResponse<List<ArticlesItem>>> =
                remoteData.getSuaraNews()

            public override fun loadFromDB(): LiveData<List<ArticleSuara>> =
                localData.getAllArticleSuara()

        }.asLiveData()
    }

    override fun getBusinessNews(): LiveData<Resource<List<ArticleBusiness>>> {
        return object : NetworkBoundResource<List<ArticleBusiness>, List<ArticlesItem>>(appExecutors){
            override fun shouldFetch(data: List<ArticleBusiness>?): Boolean =
                true

            override fun saveCallResult(data: List<ArticlesItem>?) {
                val listNews = ArrayList<ArticleBusiness>()
                if (data != null) {
                    for(response in data){
                        val article = response.title?.let {
                            ArticleBusiness(
                                author = response.author,
                                source = Source(name=response.source?.name),
                                title = it,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt,
                                content = response.content,
                                url = response.url
                            )
                        }
                        if(article!=null){
                            listNews.add(article)
                        }
                    }
                }
                localData.insertArticlesBusiness(listNews)
            }

            public override fun createCall(): LiveData<ApiResponse<List<ArticlesItem>>> =
                remoteData.getBusinessNews()

            public override fun loadFromDB(): LiveData<List<ArticleBusiness>> =
                localData.getAllArticleBusiness()

        }.asLiveData()
    }

    override fun getEntertainmentNews(): LiveData<Resource<List<ArticleEntertainment>>> {
        return object : NetworkBoundResource<List<ArticleEntertainment>, List<ArticlesItem>>(appExecutors){
            override fun shouldFetch(data: List<ArticleEntertainment>?): Boolean =
                true

            override fun saveCallResult(data: List<ArticlesItem>?) {
                val listNews = ArrayList<ArticleEntertainment>()
                if (data != null) {
                    for(response in data){
                        val article = response.title?.let {
                            ArticleEntertainment(
                                author = response.author,
                                source = Source(name=response.source?.name),
                                title = it,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt,
                                content = response.content,
                                url = response.url
                            )
                        }
                        if(article!=null){
                            listNews.add(article)
                        }
                    }
                }
                localData.insertArticlesEntertainment(listNews)
            }

            public override fun createCall(): LiveData<ApiResponse<List<ArticlesItem>>> =
                remoteData.getEntertainmentNews()

            public override fun loadFromDB(): LiveData<List<ArticleEntertainment>> =
                localData.getAllArticleEntertainment()

        }.asLiveData()
    }

    override fun getGeneralNews(): LiveData<Resource<List<ArticleGeneral>>> {
        return object : NetworkBoundResource<List<ArticleGeneral>, List<ArticlesItem>>(appExecutors){
            override fun shouldFetch(data: List<ArticleGeneral>?): Boolean =
                true

            override fun saveCallResult(data: List<ArticlesItem>?) {
                val listNews = ArrayList<ArticleGeneral>()
                if (data != null) {
                    for(response in data){
                        val article = response.title?.let {
                            ArticleGeneral(
                                author = response.author,
                                source = Source(name=response.source?.name),
                                title = it,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt,
                                content = response.content,
                                url = response.url
                            )
                        }
                        if(article!=null){
                            listNews.add(article)
                        }
                    }
                }
                localData.insertArticlesGeneral(listNews)
            }

            public override fun createCall(): LiveData<ApiResponse<List<ArticlesItem>>> =
                remoteData.getGeneralNews()

            public override fun loadFromDB(): LiveData<List<ArticleGeneral>> =
                localData.getAllArticleGeneral()

        }.asLiveData()
    }

    override fun getHealthNews(): LiveData<Resource<List<ArticleHealth>>> {
        return object : NetworkBoundResource<List<ArticleHealth>, List<ArticlesItem>>(appExecutors){
            override fun shouldFetch(data: List<ArticleHealth>?): Boolean =
                true

            override fun saveCallResult(data: List<ArticlesItem>?) {
                val listNews = ArrayList<ArticleHealth>()
                if (data != null) {
                    for(response in data){
                        val article = response.title?.let {
                            ArticleHealth(
                                author = response.author,
                                source = Source(name=response.source?.name),
                                title = it,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt,
                                content = response.content,
                                url = response.url
                            )
                        }
                        if(article!=null){
                            listNews.add(article)
                        }
                    }
                }
                localData.insertArticlesHealth(listNews)
            }

            public override fun createCall(): LiveData<ApiResponse<List<ArticlesItem>>> =
                remoteData.getHealthNews()

            public override fun loadFromDB(): LiveData<List<ArticleHealth>> =
                localData.getAllArticleHealth()

        }.asLiveData()
    }

    override fun getScienceNews(): LiveData<Resource<List<ArticleScience>>> {
        return object : NetworkBoundResource<List<ArticleScience>, List<ArticlesItem>>(appExecutors){
            override fun shouldFetch(data: List<ArticleScience>?): Boolean =
                true

            override fun saveCallResult(data: List<ArticlesItem>?) {
                val listNews = ArrayList<ArticleScience>()
                if (data != null) {
                    for(response in data){
                        val article = response.title?.let {
                            ArticleScience(
                                author = response.author,
                                source = Source(name=response.source?.name),
                                title = it,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt,
                                content = response.content,
                                url = response.url
                            )
                        }
                        if(article!=null){
                            listNews.add(article)
                        }
                    }
                }
                localData.insertArticlesScience(listNews)
            }

            public override fun createCall(): LiveData<ApiResponse<List<ArticlesItem>>> =
                remoteData.getScienceNews()

            public override fun loadFromDB(): LiveData<List<ArticleScience>> =
                localData.getAllArticleScience()

        }.asLiveData()
    }

    override fun getSportsNews(): LiveData<Resource<List<ArticleSports>>> {
        return object : NetworkBoundResource<List<ArticleSports>, List<ArticlesItem>>(appExecutors){
            override fun shouldFetch(data: List<ArticleSports>?): Boolean =
                true

            override fun saveCallResult(data: List<ArticlesItem>?) {
                val listNews = ArrayList<ArticleSports>()
                if (data != null) {
                    for(response in data){
                        val article = response.title?.let {
                            ArticleSports(
                                author = response.author,
                                source = Source(name=response.source?.name),
                                title = it,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt,
                                content = response.content,
                                url = response.url
                            )
                        }
                        if(article!=null){
                            listNews.add(article)
                        }
                    }
                }
                localData.insertArticlesSports(listNews)
            }

            public override fun createCall(): LiveData<ApiResponse<List<ArticlesItem>>> =
                remoteData.getSportsNews()

            public override fun loadFromDB(): LiveData<List<ArticleSports>> =
                localData.getAllArticleSports()

        }.asLiveData()
    }

    override fun getTechnologyNews(): LiveData<Resource<List<ArticleTechnology>>> {
        return object : NetworkBoundResource<List<ArticleTechnology>, List<ArticlesItem>>(appExecutors){
            override fun shouldFetch(data: List<ArticleTechnology>?): Boolean =
                true

            override fun saveCallResult(data: List<ArticlesItem>?) {
                val listNews = ArrayList<ArticleTechnology>()
                if (data != null) {
                    for(response in data){
                        val article = response.title?.let {
                            ArticleTechnology(
                                author = response.author,
                                source = Source(name=response.source?.name),
                                title = it,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt,
                                content = response.content,
                                url = response.url
                            )
                        }
                        if(article!=null){
                            listNews.add(article)
                        }
                    }
                }
                localData.insertArticlesTechnology(listNews)
            }

            public override fun createCall(): LiveData<ApiResponse<List<ArticlesItem>>> =
                remoteData.getTechnologyNews()

            public override fun loadFromDB(): LiveData<List<ArticleTechnology>> =
                localData.getAllArticleTechnology()

        }.asLiveData()
    }

    override fun setFavoriteTechnology(article: ArticleTechnology, state: Boolean) {
        appExecutors.diskIO().execute { localData.setFavoriteTechnology(article, state) }
    }

    override fun getFavoritesTechnology(): LiveData<List<ArticleTechnology>> = localData.getAllFavoritesTechnology()

    override fun setFavoriteSports(article: ArticleSports, state: Boolean) {
        appExecutors.diskIO().execute { localData.setFavoriteSports(article, state) }
    }

    override fun getFavoritesSports(): LiveData<List<ArticleSports>> = localData.getAllFavoritesSports()

    override fun setFavoriteScience(article: ArticleScience, state: Boolean) {
        appExecutors.diskIO().execute { localData.setFavoriteScience(article, state) }
    }

    override fun getFavoritesScience(): LiveData<List<ArticleScience>> = localData.getAllFavoritesScience()

    override fun setFavoriteHealth(article: ArticleHealth, state: Boolean) {
        appExecutors.diskIO().execute { localData.setFavoriteHealth(article, state) }
    }

    override fun getFavoritesHealth(): LiveData<List<ArticleHealth>> = localData.getAllFavoritesHealth()

    override fun setFavoriteGeneral(article: ArticleGeneral, state: Boolean) {
        appExecutors.diskIO().execute { localData.setFavoriteGeneral(article, state) }
    }

    override fun getFavoritesGeneral(): LiveData<List<ArticleGeneral>> = localData.getAllFavoritesGeneral()

    override fun setFavoriteEntertainment(article: ArticleEntertainment, state: Boolean) {
        appExecutors.diskIO().execute { localData.setFavoriteEntertainment(article, state) }
    }

    override fun getFavoritesEntertainment(): LiveData<List<ArticleEntertainment>> = localData.getAllFavoritesEntertainment()

    override fun setFavoriteBusiness(article: ArticleBusiness, state: Boolean) {
        appExecutors.diskIO().execute { localData.setFavoriteBusiness(article, state) }
    }

    override fun getFavoritesBusiness(): LiveData<List<ArticleBusiness>> = localData.getAllFavoritesBusiness()

    override fun setFavoriteSuara(article: ArticleSuara, state: Boolean) {
        appExecutors.diskIO().execute { localData.setFavoriteSuara(article, state) }
    }

    override fun getFavoritesSuara(): LiveData<List<ArticleSuara>> = localData.getAllFavoritesSuara()

    override fun setFavoriteKapanlagi(article: ArticleKapanlagi, state: Boolean) {
        appExecutors.diskIO().execute { localData.setFavoriteKapanlagi(article, state) }
    }

    override fun getFavoritesKapanlagi(): LiveData<List<ArticleKapanlagi>> = localData.getAllFavoritesKapanlagi()

    override fun setFavoriteViva(article: ArticleViva, state: Boolean) {
        appExecutors.diskIO().execute { localData.setFavoriteViva(article, state) }
    }

    override fun getFavoritesViva(): LiveData<List<ArticleViva>> = localData.getAllFavoritesViva()

    override fun setFavoriteDetik(article: ArticleDetik, state: Boolean) {
        appExecutors.diskIO().execute { localData.setFavoriteDetik(article, state) }
    }

    override fun getFavoritesDetik(): LiveData<List<ArticleDetik>> = localData.getAllFavoritesDetik()

    override fun setFavoriteHeadline(article: ArticleHeadline, state: Boolean) {
        appExecutors.diskIO().execute { localData.setFavoriteHeadline(article, state) }
    }

    override fun getFavoritesHeadline(): LiveData<List<ArticleHeadline>> = localData.getAllFavoritesHeadline()

    override fun getDetailTopHeadlines(content: String): LiveData<ArticleHeadline> =
        localData.getDetailHeadline(content)

    override fun getDetailDetikNews(content: String): LiveData<ArticleDetik> =
        localData.getDetailDetik(content)

    override fun getDetailVivaNews(content: String): LiveData<ArticleViva> =
        localData.getDetailViva(content)

    override fun getDetailKapanlagiNews(content: String): LiveData<ArticleKapanlagi> =
        localData.getDetailKapanlagi(content)

    override fun getDetailSuaraNews(content: String): LiveData<ArticleSuara> =
        localData.getDetailSuara(content)

    override fun getDetailBusinessNews(content: String): LiveData<ArticleBusiness> =
        localData.getDetailBusiness(content)

    override fun getDetailEntertainmentNews(content: String): LiveData<ArticleEntertainment> =
        localData.getDetailEntertainment(content)

    override fun getDetailGeneralNews(content: String): LiveData<ArticleGeneral> =
        localData.getDetailGeneral(content)

    override fun getDetailHealthNews(content: String): LiveData<ArticleHealth> =
        localData.getDetailHealth(content)

    override fun getDetailScienceNews(content: String): LiveData<ArticleScience> =
        localData.getDetailScience(content)

    override fun getDetailSportsNews(content: String): LiveData<ArticleSports> =
        localData.getDetailSports(content)

    override fun getDetailTechnologyNews(content: String): LiveData<ArticleTechnology> =
        localData.getDetailTechnology(content)

}
