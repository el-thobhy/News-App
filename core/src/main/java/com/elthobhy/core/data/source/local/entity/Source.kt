package com.elthobhy.core.data.source.local.entity


import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(
    @ColumnInfo(name = "id")
    val id: String? = null,

    @ColumnInfo(name = "name" )
    val name: String?=null,
) : Parcelable