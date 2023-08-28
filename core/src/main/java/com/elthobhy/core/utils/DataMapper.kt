package com.elthobhy.core.utils

import com.elthobhy.core.data.source.local.entity.Source
import com.elthobhy.core.data.source.local.entity.headline.ArticleHeadlineEntity
import com.elthobhy.core.data.source.remote.response.ArticlesItem
import com.elthobhy.core.domain.model.Domain

object DataMapper {
    fun mapResponseToEntity(input: List<ArticlesItem>): List<ArticleHeadlineEntity>{
        val out = ArrayList<ArticleHeadlineEntity>()
        input.map{
            val list = it.title?.let { it1 ->
                ArticleHeadlineEntity(
                    author = it.author,
                    source = Source(name = it.source?.name),
                    title = it1,
                    urlToImage = it.urlToImage,
                    publishedAt = it.publishedAt,
                    content = it.content,
                    url = it.url
                )
            }
            if (list != null) {
                out.add(list)
            }
        }
        return out
    }
    fun mapEntityToDomain(input: List<ArticleHeadlineEntity>): List<Domain>{
        val out = ArrayList<Domain>()
        input.map {
            val list = Domain(
                author = it.author,
                source = Source(name = it.source?.name),
                title = it.title,
                urlToImage = it.urlToImage,
                publishedAt = it.publishedAt,
                content = it.content,
                url = it.url
            )
            out.add(list)
        }
        return out
    }
    fun itemEntityToDomain(input: ArticleHeadlineEntity): Domain {
        return Domain(
            author = input.author,
            source = Source(name = input.source?.name),
            title = input.title,
            urlToImage = input.urlToImage,
            publishedAt = input.publishedAt,
            content = input.content,
            url = input.url
        )
    }
}