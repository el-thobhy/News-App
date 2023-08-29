package com.elthobhy.core.domain.model

import android.os.Parcelable
import com.elthobhy.core.data.source.local.entity.Source
import kotlinx.parcelize.Parcelize

@Parcelize
class Domain(
    val publishedAt: String? = null,
    val author: String? =null,
    val content: String? = null,
    val description: String? =null,
    var source: Source? =null,
    val title: String,
    val url: String? =null,
    val urlToImage: String? =null,
    var bookmarked: Boolean = false,
    var detik:Boolean = false,
    var suara:Boolean = false,
    var kapanlagi:Boolean = false,
    var liputan:Boolean = false
):Parcelable