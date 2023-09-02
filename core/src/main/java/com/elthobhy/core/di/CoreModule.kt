package com.elthobhy.core.di

import androidx.room.Room
import com.elthobhy.core.BuildConfig
import com.elthobhy.core.data.source.local.LocalData
import com.elthobhy.core.data.source.local.room.ArticleDatabase
import com.elthobhy.core.data.source.remote.RemoteData
import com.elthobhy.core.data.source.remote.network.ApiService
import com.elthobhy.core.domain.repository.CatalogNewsDataSource
import com.elthobhy.core.utils.AppExecutors
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networking = module {
    single{
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val databaseModule = module {
    single { Room.databaseBuilder(
        androidContext(), ArticleDatabase::class.java, "article-db"
    ).fallbackToDestructiveMigration().build() }

    factory { get<ArticleDatabase>().articleDao() }
}
val repositoryModule = module {
    val firebaseAuth = FirebaseAuth.getInstance()
    val user =
        FirebaseDatabase.getInstance("https://news-app-9b6f9-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .getReference(
                "users"
            )

    single { RemoteData(firebaseAuth, user) }
    single { LocalData.getInstace(get()) }
    single<CatalogNewsDataSource> {
        com.elthobhy.core.data.source.CatalogNewsRepository(
            get(),
            get(),
            get()
        )
    }
    single { AppExecutors() }
}