package com.elthobhy.newsapp

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import com.elthobhy.core.di.databaseModule
import com.elthobhy.core.di.networking
import com.elthobhy.core.di.repositoryModule
import com.elthobhy.newsapp.di.adapterModule
import com.elthobhy.newsapp.di.useCase
import com.elthobhy.newsapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate: MyApp", )
        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            modules(
                networking,
                databaseModule,
                repositoryModule,
                viewModelModule,
                adapterModule,
                useCase
            )
        }
    }
}