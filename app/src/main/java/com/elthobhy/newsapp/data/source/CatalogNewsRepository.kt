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


}
