package com.elthobhy.newsapp.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elthobhy.newsapp.data.source.local.entity.business.ArticleBusiness
import com.elthobhy.newsapp.data.source.local.entity.detik.ArticleDetik
import com.elthobhy.newsapp.data.source.local.entity.entertainment.ArticleEntertainment
import com.elthobhy.newsapp.data.source.local.entity.general.ArticleGeneral
import com.elthobhy.newsapp.data.source.local.entity.headline.ArticleHeadline
import com.elthobhy.newsapp.data.source.local.entity.health.ArticleHealth
import com.elthobhy.newsapp.data.source.local.entity.kapanlagi.ArticleKapanlagi
import com.elthobhy.newsapp.data.source.local.entity.science.ArticleScience
import com.elthobhy.newsapp.data.source.local.entity.sports.ArticleSports
import com.elthobhy.newsapp.data.source.local.entity.suara.ArticleSuara
import com.elthobhy.newsapp.data.source.local.entity.technology.ArticleTechnology
import com.elthobhy.newsapp.data.source.local.entity.viva.ArticleViva

@Database(entities = [
    ArticleHeadline::class,
    ArticleBusiness::class,
    ArticleEntertainment::class,
    ArticleGeneral::class,
    ArticleHealth::class,
    ArticleScience::class,
    ArticleSports::class,
    ArticleTechnology::class,
    ArticleDetik::class,
    ArticleViva::class,
    ArticleKapanlagi::class,
    ArticleSuara::class,
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