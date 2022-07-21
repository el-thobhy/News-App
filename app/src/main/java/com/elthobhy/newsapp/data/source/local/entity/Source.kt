package com.elthobhy.newsapp.data.source.local.entity


import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Parcelize
data class Source(
    @ColumnInfo(name = "id")
    val id: String? = null,

    @ColumnInfo(name = "name" )
    val name: String?=null,
) : Parcelable