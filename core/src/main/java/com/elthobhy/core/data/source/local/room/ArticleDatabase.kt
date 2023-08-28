package com.elthobhy.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elthobhy.core.data.source.local.entity.headline.ArticleHeadlineEntity

@Database(entities = [
    ArticleHeadlineEntity::class,
], version = 1, exportSchema = false)
abstract class ArticleDatabase: RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object{
        private var INSTANCE: ArticleDatabase? = null

        fun getInstance(context: Context): ArticleDatabase =
            INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDatabase::class.java,
                "article-db"
                ).build().apply { INSTANCE = this }
            }
    }
}