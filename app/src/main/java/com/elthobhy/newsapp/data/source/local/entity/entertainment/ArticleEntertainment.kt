package com.elthobhy.newsapp.data.source.local.entity.entertainment

import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.elthobhy.newsapp.data.source.local.entity.Source
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "entertainment_entities")
class ArticleEntertainment (
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String? = null,

    @ColumnInfo(name = "author")
    val author: String? =null,

    @ColumnInfo(name = "content")
    val content: String?=null,

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
    var bookmarked: Boolean  = false,
): Parcelable