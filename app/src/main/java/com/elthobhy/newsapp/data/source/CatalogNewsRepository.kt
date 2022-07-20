package com.elthobhy.newsapp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elthobhy.newsapp.data.source.local.entity.Article
import com.elthobhy.newsapp.data.source.local.entity.Source
import com.elthobhy.newsapp.data.source.remote.RemoteData
import com.elthobhy.newsapp.data.source.remote.response.ArticlesItem

class CatalogNewsRepository private constructor(private val remoteData: RemoteData):
    CatalogNewsDataSource {
        companion object{
            @Volatile
            private var instance : CatalogNewsRepository? = null
            fun getInstance(remoteData: RemoteData): CatalogNewsRepository =
                instance ?: synchronized(this) {
                    instance ?: CatalogNewsRepository(remoteData).apply {
                        instance = this
                    }
                }
        }

    override fun getTopHeadlines(): LiveData<List<Article>> {
        val listNews = MutableLiveData<List<Article>>()
        remoteData.getTopHeadlines(object : RemoteData.LoadTopHeadlinesCallback{
            override fun onAllTopHeadlinesReceived(topResponse: List<ArticlesItem?>) {
                val topNews = ArrayList<Article>()
                if(topResponse.isNotEmpty()){
                    for(response in topResponse){
                        if(response != null){
                            val articleNews = Article(
                                source = Source(name=response.source?.name),
                                title = response.title,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt
                            )
                            topNews.add(articleNews)
                        }
                    }
                    listNews.postValue(topNews)
                } else{
                    listNews.postValue(topNews)
                }
            }

        })
        return listNews
    }

    override fun getDetikNews(): LiveData<List<Article>> {
        val listNews = MutableLiveData<List<Article>>()
        remoteData.getDetikNews(object : RemoteData.LoadDetikNewsCallback{
            override fun onAllDetikReceived(detikResponse: List<ArticlesItem?>) {
                val detikNews = ArrayList<Article>()
                if(detikResponse.isNotEmpty()){
                    for(response in detikResponse){
                        if(response != null){
                            val articleNews = Article(
                                source = Source(name=response.source?.name),
                                title = response.title,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt
                            )
                            detikNews.add(articleNews)
                        }
                    }
                    listNews.postValue(detikNews)
                }else{
                    listNews.postValue(detikNews)
                }
            }
        })
        return listNews
    }

    override fun getVivaNews(): LiveData<List<Article>> {
        val listNews = MutableLiveData<List<Article>>()
        remoteData.getVivaNews(object : RemoteData.LoadVivaNewsCallback{
            override fun onAllVivaReceived(vivaResponse: List<ArticlesItem?>) {
                val vivaNews = ArrayList<Article>()
                if(vivaResponse.isNotEmpty()){
                    for(response in vivaResponse){
                        if(response!=null){
                            val articleNews = Article(
                                source = Source(name=response.source?.name),
                                title = response.title,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt
                            )
                            vivaNews.add(articleNews)
                        }
                    }
                    listNews.postValue(vivaNews)
                }else{
                    listNews.postValue(vivaNews)
                }
            }

        })
        return listNews
    }

    override fun getKapanlagiNews(): LiveData<List<Article>> {
        val listNews = MutableLiveData<List<Article>>()
        remoteData.getKapanlagiNews(object :RemoteData.LoadKapanlagiCallback{
            override fun onAllKapanlagiReceived(kapanlagiResponse: List<ArticlesItem?>) {
                val kapanlagiNews = ArrayList<Article>()
                if(kapanlagiResponse.isNotEmpty()){
                    for(response in kapanlagiResponse){
                        if(response != null){
                            val articleNews = Article(
                                source = Source(name=response.source?.name),
                                title = response.title,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt
                            )
                            kapanlagiNews.add(articleNews)
                        }
                    }
                    listNews.postValue(kapanlagiNews)
                }else{
                    listNews.postValue(kapanlagiNews)
                }
            }
        })
        return listNews
    }

    override fun getSuaraNews(): LiveData<List<Article>> {
        val listNews = MutableLiveData<List<Article>>()
        remoteData.getSuaraNews(object :RemoteData.LoadSuaraNewsCallback{
            override fun onAllSuaraReceived(suaraResponse: List<ArticlesItem?>) {
                val suaraNews = ArrayList<Article>()
                if(suaraResponse.isNotEmpty()){
                    for(response in suaraResponse){
                        if(response != null){
                            val articleNews = Article(
                                source = Source(name=response.source?.name),
                                title = response.title,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt
                            )
                            suaraNews.add(articleNews)
                        }
                    }
                    listNews.postValue(suaraNews)
                }else{
                    listNews.postValue(suaraNews)
                }
            }

        })
        return listNews
    }

    override fun getBusinessNews(): LiveData<List<Article>> {
        val listBusiness = MutableLiveData<List<Article>>()
        remoteData.getBusinessNews(object : RemoteData.LoadBusinessCallback{
            override fun onAllBusinessReceived(businessResponse: List<ArticlesItem?>) {
                val businessNews = ArrayList<Article>()
                if(businessResponse.isNotEmpty()){
                    for(response in businessResponse){
                        if(response != null){
                            val businessArticle = Article(
                                source = Source(name=response.source?.name),
                                title = response.title,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt
                            )
                            businessNews.add(businessArticle)
                        }
                    }
                    listBusiness.postValue(businessNews)
                }else{
                    listBusiness.postValue(businessNews)
                }
            }

        })
        return  listBusiness
    }

    override fun getEntertainmentNews(): LiveData<List<Article>> {
        val listEntertainment = MutableLiveData<List<Article>>()
        remoteData.getEntertainmentNews(object : RemoteData.LoadEntertainmentCallback {
            override fun onAllEntertainmentReceived(entertainmentResponse: List<ArticlesItem?>) {
                val entertainmentNews = ArrayList<Article>()
                if (entertainmentResponse.isNotEmpty()) {
                    for (response in entertainmentResponse) {
                        if (response != null) {
                            val entertainmentArticle = Article(
                                source = Source(name = response.source?.name),
                                title = response.title,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt
                            )
                            entertainmentNews.add(entertainmentArticle)
                        }
                    }
                    listEntertainment.postValue(entertainmentNews)
                } else {
                    listEntertainment.postValue(entertainmentNews)
                }
            }
        })
        return listEntertainment
    }

    override fun getGeneralNews(): LiveData<List<Article>> {
        val listGeneral = MutableLiveData<List<Article>>()
        remoteData.getGeneralNews(object : RemoteData.LoadGeneralCallback {
            override fun onAllGeneralReceived(generalResponse: List<ArticlesItem?>) {
                val generalNews = ArrayList<Article>()
                if (generalResponse.isNotEmpty()) {
                    for (response in generalResponse) {
                        if (response != null) {
                            val generalArticle = Article(
                                source = Source(name = response.source?.name),
                                title = response.title,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt
                            )
                            generalNews.add(generalArticle)
                        }
                    }
                    listGeneral.postValue(generalNews)
                } else {
                    listGeneral.postValue(generalNews)
                }
            }
        })
        return listGeneral
    }

    override fun getHealthNews(): LiveData<List<Article>> {
        val listHealth = MutableLiveData<List<Article>>()
        remoteData.getHealthNews(object : RemoteData.LoadHealthCallback {
            override fun onAllHealthReceived(healthResponse: List<ArticlesItem?>) {
                val healthNews = ArrayList<Article>()
                if (healthResponse.isNotEmpty()) {
                    for (response in healthResponse) {
                        if (response != null) {
                            val healthArticle = Article(
                                source = Source(name = response.source?.name),
                                title = response.title,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt
                            )
                            healthNews.add(healthArticle)
                        }
                    }
                    listHealth.postValue(healthNews)
                } else {
                    listHealth.postValue(healthNews)
                }
            }
        })
        return listHealth
    }

    override fun getScienceNews(): LiveData<List<Article>> {
        val listScience = MutableLiveData<List<Article>>()
        remoteData.getScienceNews(object : RemoteData.LoadScienceCallback {
            override fun onAllScienceReceived(scienceResponse: List<ArticlesItem?>) {
                val scienceNews = ArrayList<Article>()
                if (scienceResponse.isNotEmpty()) {
                    for (response in scienceResponse) {
                        if (response != null) {
                            val scienceArticle = Article(
                                source = Source(name = response.source?.name),
                                title = response.title,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt
                            )
                            scienceNews.add(scienceArticle)
                        }
                    }
                    listScience.postValue(scienceNews)
                } else {
                    listScience.postValue(scienceNews)
                }
            }
        })
        return listScience
    }

    override fun getSportsNews(): LiveData<List<Article>> {
        val listSports = MutableLiveData<List<Article>>()
        remoteData.getSportsNews(object : RemoteData.LoadSportsCallback {
            override fun onAllSportsReceived(sportsResponse: List<ArticlesItem?>) {
                val sportsNews = ArrayList<Article>()
                if (sportsResponse.isNotEmpty()) {
                    for (response in sportsResponse) {
                        if (response != null) {
                            val sportsArticle = Article(
                                source = Source(name = response.source?.name),
                                title = response.title,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt
                            )
                            sportsNews.add(sportsArticle)
                        }
                    }
                    listSports.postValue(sportsNews)
                } else {
                    listSports.postValue(sportsNews)
                }
            }
        })
        return listSports
    }

    override fun getTechnologyNews(): LiveData<List<Article>> {
        val listTechnology = MutableLiveData<List<Article>>()
        remoteData.getTechnologyNews(object : RemoteData.LoadTechnologyCallback {
            override fun onAllTechnologyReceived(technologyResponse: List<ArticlesItem?>) {
                val technologyNews = ArrayList<Article>()
                if (technologyResponse.isNotEmpty()) {
                    for (response in technologyResponse) {
                        if (response != null) {
                            val technologyArticle = Article(
                                source = Source(name = response.source?.name),
                                title = response.title,
                                urlToImage = response.urlToImage,
                                publishedAt = response.publishedAt
                            )
                            technologyNews.add(technologyArticle)
                        }
                    }
                    listTechnology.postValue(technologyNews)
                } else {
                    listTechnology.postValue(technologyNews)
                }
            }
        })
        return listTechnology
    }


}
