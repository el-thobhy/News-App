package com.elthobhy.newsapp.utils

import com.elthobhy.newsapp.data.source.local.entity.headline.ArticleHeadline
import com.elthobhy.newsapp.data.source.local.entity.Source

object DataDummy {
    fun generateDummyHeadline(): List<ArticleHeadline>{
        val article = ArrayList<ArticleHeadline>()
        article.add(
            ArticleHeadline(
                source = Source(name="detik.com"),
                publishedAt = "2022-07-20T00:27:00Z",
                urlToImage = "https://static.republika.co.id/uploads/member/images/news/thumbnail400/8hfoamr24p.jpg",
                title = "Budidaya Ikan dan Sayuran Skala Rumahan dengan Sistem Aquaponik - signal.republika.co.id",
                content = ""
            )
        )
        article.add(
            ArticleHeadline(
                source = Source(name="detik.com"),
                publishedAt = "2022-07-20T00:27:00Z",
                urlToImage = "https://static.republika.co.id/uploads/member/images/news/thumbnail400/8hfoamr24p.jpg",
                title = "Budidaya Ikan dan Sayuran Skala Rumahan dengan Sistem Aquaponik - signal.republika.co.id",
                content = ""
            )
        )
        article.add(
            ArticleHeadline(
                source = Source(name="detik.com"),
                publishedAt = "2022-07-20T00:27:00Z",
                urlToImage = "https://static.republika.co.id/uploads/member/images/news/thumbnail400/8hfoamr24p.jpg",
                title = "Budidaya Ikan dan Sayuran Skala Rumahan dengan Sistem Aquaponik - signal.republika.co.id",
                content = ""
            )
        )
        article.add(
            ArticleHeadline(
                source = Source(name="detik.com"),
                publishedAt = "2022-07-20T00:27:00Z",
                urlToImage = "https://static.republika.co.id/uploads/member/images/news/thumbnail400/8hfoamr24p.jpg",
                title = "Budidaya Ikan dan Sayuran Skala Rumahan dengan Sistem Aquaponik - signal.republika.co.id",
                content = ""
            )
        )
        article.add(
            ArticleHeadline(
                source = Source(name="detik.com"),
                publishedAt = "2022-07-20T00:27:00Z",
                urlToImage = "https://static.republika.co.id/uploads/member/images/news/thumbnail400/8hfoamr24p.jpg",
                title = "Budidaya Ikan dan Sayuran Skala Rumahan dengan Sistem Aquaponik - signal.republika.co.id",
                content = ""
            )
        )
        article.add(
            ArticleHeadline(
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