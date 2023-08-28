package com.elthobhy.core.utils

import com.elthobhy.core.data.source.local.entity.Source
import com.elthobhy.core.data.source.local.entity.headline.ArticleHeadlineEntity

object DataDummy {
    fun generateDummyHeadline(): List<ArticleHeadlineEntity>{
        val article = ArrayList<ArticleHeadlineEntity>()
        article.add(
            ArticleHeadlineEntity(
                source = Source(name="detik.com"),
                publishedAt = "2022-07-20T00:27:00Z",
                urlToImage = "https://static.republika.co.id/uploads/member/images/news/thumbnail400/8hfoamr24p.jpg",
                title = "Budidaya Ikan dan Sayuran Skala Rumahan dengan Sistem Aquaponik - signal.republika.co.id",
                content = ""
            )
        )
        article.add(
            ArticleHeadlineEntity(
                source = Source(name="detik.com"),
                publishedAt = "2022-07-20T00:27:00Z",
                urlToImage = "https://static.republika.co.id/uploads/member/images/news/thumbnail400/8hfoamr24p.jpg",
                title = "Budidaya Ikan dan Sayuran Skala Rumahan dengan Sistem Aquaponik - signal.republika.co.id",
                content = ""
            )
        )
        article.add(
            ArticleHeadlineEntity(
                source = Source(name="detik.com"),
                publishedAt = "2022-07-20T00:27:00Z",
                urlToImage = "https://static.republika.co.id/uploads/member/images/news/thumbnail400/8hfoamr24p.jpg",
                title = "Budidaya Ikan dan Sayuran Skala Rumahan dengan Sistem Aquaponik - signal.republika.co.id",
                content = ""
            )
        )
        article.add(
            ArticleHeadlineEntity(
                source = Source(name="detik.com"),
                publishedAt = "2022-07-20T00:27:00Z",
                urlToImage = "https://static.republika.co.id/uploads/member/images/news/thumbnail400/8hfoamr24p.jpg",
                title = "Budidaya Ikan dan Sayuran Skala Rumahan dengan Sistem Aquaponik - signal.republika.co.id",
                content = ""
            )
        )
        article.add(
            ArticleHeadlineEntity(
                source = Source(name="detik.com"),
                publishedAt = "2022-07-20T00:27:00Z",
                urlToImage = "https://static.republika.co.id/uploads/member/images/news/thumbnail400/8hfoamr24p.jpg",
                title = "Budidaya Ikan dan Sayuran Skala Rumahan dengan Sistem Aquaponik - signal.republika.co.id",
                content = ""
            )
        )
        article.add(
            ArticleHeadlineEntity(
                source = Source(name="detik.com"),
                publishedAt = "2022-07-20T00:27:00Z",
                urlToImage = "https://static.republika.co.id/uploads/member/images/news/thumbnail400/8hfoamr24p.jpg",
                title = "Budidaya Ikan dan Sayuran Skala Rumahan dengan Sistem Aquaponik - signal.republika.co.id",
                content = ""
            )
        )

        return article
    }
}