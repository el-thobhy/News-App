package com.elthobhy.newsapp.utils

import com.elthobhy.newsapp.data.source.local.entity.Article
import com.elthobhy.newsapp.data.source.local.entity.Source

object DataDummy {
    fun generateDummyHeadline(): List<Article>{
        val article = ArrayList<Article>()
        article.add(
            Article(
                source = Source(name="detik.com"),
                publishedAt = "2022-07-20T00:27:00Z",
                urlToImage = "https://static.republika.co.id/uploads/member/images/news/thumbnail400/8hfoamr24p.jpg",
                title = "Budidaya Ikan dan Sayuran Skala Rumahan dengan Sistem Aquaponik - signal.republika.co.id"
            )
        )
        article.add(
            Article(
                source = Source(name="detik.com"),
                publishedAt = "2022-07-20T00:27:00Z",
                urlToImage = "https://static.republika.co.id/uploads/member/images/news/thumbnail400/8hfoamr24p.jpg",
                title = "Budidaya Ikan dan Sayuran Skala Rumahan dengan Sistem Aquaponik - signal.republika.co.id"
            )
        )
        article.add(
            Article(
                source = Source(name="detik.com"),
                publishedAt = "2022-07-20T00:27:00Z",
                urlToImage = "https://static.republika.co.id/uploads/member/images/news/thumbnail400/8hfoamr24p.jpg",
                title = "Budidaya Ikan dan Sayuran Skala Rumahan dengan Sistem Aquaponik - signal.republika.co.id"
            )
        )
        article.add(
            Article(
                source = Source(name="detik.com"),
                publishedAt = "2022-07-20T00:27:00Z",
                urlToImage = "https://static.republika.co.id/uploads/member/images/news/thumbnail400/8hfoamr24p.jpg",
                title = "Budidaya Ikan dan Sayuran Skala Rumahan dengan Sistem Aquaponik - signal.republika.co.id"
            )
        )
        article.add(
            Article(
                source = Source(name="detik.com"),
                publishedAt = "2022-07-20T00:27:00Z",
                urlToImage = "https://static.republika.co.id/uploads/member/images/news/thumbnail400/8hfoamr24p.jpg",
                title = "Budidaya Ikan dan Sayuran Skala Rumahan dengan Sistem Aquaponik - signal.republika.co.id"
            )
        )
        article.add(
            Article(
                source = Source(name="detik.com"),
                publishedAt = "2022-07-20T00:27:00Z",
                urlToImage = "https://static.republika.co.id/uploads/member/images/news/thumbnail400/8hfoamr24p.jpg",
                title = "Budidaya Ikan dan Sayuran Skala Rumahan dengan Sistem Aquaponik - signal.republika.co.id"
            )
        )

        return article
    }
}