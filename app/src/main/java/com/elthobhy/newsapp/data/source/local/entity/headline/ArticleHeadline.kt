package com.elthobhy.newsapp.data.source.local.entity.headline


import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.*
import com.elthobhy.newsapp.data.source.local.entity.Source

@Parcelize
@Entity(tableName = "article_entities")
data class ArticleHeadline(

    @ColumnInfo(name = "publishedAt")
    val publishedAt: String? = null,

    @ColumnInfo(name = "author")
    val author: String? =null,


    @ColumnInfo(name = "content")
    val content: String? = null,

    @ColumnInfo(name = "description")
    val description: String? =null,

    @Embedded
    var source: Source? =null,

    @PrimaryKey
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "url")
    val url: String? =null,

    @ColumnInfo(name = "urlToImage")
    val urlToImage: String? =null,

    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean = false,
) : Parcelable