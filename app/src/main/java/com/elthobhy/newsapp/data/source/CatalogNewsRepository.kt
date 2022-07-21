package com.elthobhy.newsapp.data.source

import androidx.lifecycle.LiveData
import com.elthobhy.newsapp.data.source.local.LocalData
import com.elthobhy.newsapp.data.source.local.entity.*
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
                data == null || data.isEmpty()

            override fun saveCallResult(data: List<ArticlesItem>) {
                val listNews = ArrayList<ArticleHeadline>()
                for(response in data){
                    val article = response.author?.let {
                        response.publishedAt?.let { it1 ->
                            response.content?.let { it2 ->
                                ArticleHeadline(
                                    author = it,
                                    source = Source(name=response.source?.name),
                                    title = response.title,
                                    urlToImage = response.urlToImage,
                                    publishedAt = it1,
                                    content = it2,
                                    url = response.url
                                )
                            }
                        }
                    }
                    if(article!=null){
                        listNews.add(article)
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
                data == null || data.isEmpty()

            override fun saveCallResult(data: List<ArticlesItem>) {
                val listNews = ArrayList<ArticleDetik>()
                for(response in data){
                    val article = response.author?.let {
                        response.publishedAt?.let { it1 ->
                            response.content?.let { it2 ->
                                ArticleDetik(
                                    author = it,
                                    source = Source(name=response.source?.name),
                                    title = response.title,
                                    urlToImage = response.urlToImage,
                                    publishedAt = it1,
                                    content = it2,
                                    url = response.url
                                )
                            }
                        }
                    }
                    if(article!=null){
                        listNews.add(article)
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
                data == null || data.isEmpty()

            override fun saveCallResult(data: List<ArticlesItem>) {
                val listNews = ArrayList<ArticleViva>()
                for(response in data){
                    val article = response.author?.let {
                        response.publishedAt?.let { it1 ->
                            response.content?.let { it2 ->
                                ArticleViva(
                                    author = it,
                                    source = Source(name=response.source?.name),
                                    title = response.title,
                                    urlToImage = response.urlToImage,
                                    publishedAt = it1,
                                    content = it2,
                                    url = response.url
                                )
                            }
                        }
                    }
                    if(article!=null){
                        listNews.add(article)
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
                data == null || data.isEmpty()

            override fun saveCallResult(data: List<ArticlesItem>) {
                val listNews = ArrayList<ArticleKapanlagi>()
                for(response in data){
                    val article = response.author?.let {
                        response.publishedAt?.let { it1 ->
                            response.content?.let { it2 ->
                                ArticleKapanlagi(
                                    author = it,
                                    source = Source(name=response.source?.name),
                                    title = response.title,
                                    urlToImage = response.urlToImage,
                                    publishedAt = it1,
                                    content = it2,
                                    url = response.url
                                )
                            }
                        }
                    }
                    if(article!=null){
                        listNews.add(article)
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
                data == null || data.isEmpty()

            override fun saveCallResult(data: List<ArticlesItem>) {
                val listNews = ArrayList<ArticleSuara>()
                for(response in data){
                    val article = response.author?.let {
                        response.publishedAt?.let { it1 ->
                            response.content?.let { it2 ->
                                ArticleSuara(
                                    author = it,
                                    source = Source(name=response.source?.name),
                                    title = response.title,
                                    urlToImage = response.urlToImage,
                                    publishedAt = it1,
                                    content = it2,
                                    url = response.url
                                )
                            }
                        }
                    }
                    if(article!=null){
                        listNews.add(article)
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
                data == null || data.isEmpty()

            override fun saveCallResult(data: List<ArticlesItem>) {
                val listNews = ArrayList<ArticleBusiness>()
                for(response in data){
                    val article = response.author?.let {
                        response.publishedAt?.let { it1 ->
                            response.content?.let { it2 ->
                                ArticleBusiness(
                                    author = it,
                                    source = Source(name=response.source?.name),
                                    title = response.title,
                                    urlToImage = response.urlToImage,
                                    publishedAt = it1,
                                    content = it2,
                                    url = response.url
                                )
                            }
                        }
                    }
                    if(article!=null){
                        listNews.add(article)
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
                data == null || data.isEmpty()

            override fun saveCallResult(data: List<ArticlesItem>) {
                val listNews = ArrayList<ArticleEntertainment>()
                for(response in data){
                    val article = response.author?.let {
                        response.publishedAt?.let { it1 ->
                            response.content?.let { it2 ->
                                ArticleEntertainment(
                                    author = it,
                                    source = Source(name=response.source?.name),
                                    title = response.title,
                                    urlToImage = response.urlToImage,
                                    publishedAt = it1,
                                    content = it2,
                                    url = response.url
                                )
                            }
                        }
                    }
                    if(article!=null){
                        listNews.add(article)
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
                data == null || data.isEmpty()

            override fun saveCallResult(data: List<ArticlesItem>) {
                val listNews = ArrayList<ArticleGeneral>()
                for(response in data){
                    val article = response.author?.let {
                        response.publishedAt?.let { it1 ->
                            response.content?.let { it2 ->
                                ArticleGeneral(
                                    author = it,
                                    source = Source(name=response.source?.name),
                                    title = response.title,
                                    urlToImage = response.urlToImage,
                                    publishedAt = it1,
                                    content = it2,
                                    url = response.url
                                )
                            }
                        }
                    }
                    if(article!=null){
                        listNews.add(article)
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
                data == null || data.isEmpty()

            override fun saveCallResult(data: List<ArticlesItem>) {
                val listNews = ArrayList<ArticleHealth>()
                for(response in data){
                    val article = response.author?.let {
                        response.publishedAt?.let { it1 ->
                            response.content?.let { it2 ->
                                ArticleHealth(
                                    author = it,
                                    source = Source(name=response.source?.name),
                                    title = response.title,
                                    urlToImage = response.urlToImage,
                                    publishedAt = it1,
                                    content = it2,
                                    url = response.url
                                )
                            }
                        }
                    }
                    if(article!=null){
                        listNews.add(article)
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
                data == null || data.isEmpty()

            override fun saveCallResult(data: List<ArticlesItem>) {
                val listNews = ArrayList<ArticleScience>()
                for(response in data){
                    val article = response.author?.let {
                        response.publishedAt?.let { it1 ->
                            response.content?.let { it2 ->
                                ArticleScience(
                                    author = it,
                                    source = Source(name=response.source?.name),
                                    title = response.title,
                                    urlToImage = response.urlToImage,
                                    publishedAt = it1,
                                    content = it2,
                                    url = response.url
                                )
                            }
                        }
                    }
                    if(article!=null){
                        listNews.add(article)
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
                data == null || data.isEmpty()

            override fun saveCallResult(data: List<ArticlesItem>) {
                val listNews = ArrayList<ArticleSports>()
                for(response in data){
                    val article = response.author?.let {
                        response.publishedAt?.let { it1 ->
                            response.content?.let { it2 ->
                                ArticleSports(
                                    author = it,
                                    source = Source(name=response.source?.name),
                                    title = response.title,
                                    urlToImage = response.urlToImage,
                                    publishedAt = it1,
                                    content = it2,
                                    url = response.url
                                )
                            }
                        }
                    }
                    if(article!=null){
                        listNews.add(article)
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
                data == null || data.isEmpty()

            override fun saveCallResult(data: List<ArticlesItem>) {
                val listNews = ArrayList<ArticleTechnology>()
                for(response in data){
                    val article = response.author?.let {
                        response.publishedAt?.let { it1 ->
                            response.content?.let { it2 ->
                                ArticleTechnology(
                                    author = it,
                                    source = Source(name=response.source?.name),
                                    title = response.title,
                                    urlToImage = response.urlToImage,
                                    publishedAt = it1,
                                    content = it2,
                                    url = response.url
                                )
                            }
                        }
                    }
                    if(article!=null){
                        listNews.add(article)
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


}
