package com.elthobhy.newsapp.di

import androidx.room.Room
import com.elthobhy.newsapp.BuildConfig
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.LocalData
import com.elthobhy.newsapp.data.source.local.room.ArticleDatabase
import com.elthobhy.newsapp.data.source.remote.RemoteData
import com.elthobhy.newsapp.data.source.remote.network.ApiService
import com.elthobhy.newsapp.ui.category.business.BusinessAdapter
import com.elthobhy.newsapp.ui.category.entertainment.EntertainmentAdapter
import com.elthobhy.newsapp.ui.category.general.GeneralAdapter
import com.elthobhy.newsapp.ui.category.health.HealthAdapter
import com.elthobhy.newsapp.ui.category.science.ScienceAdapter
import com.elthobhy.newsapp.ui.category.sport.SportsAdapter
import com.elthobhy.newsapp.ui.category.technology.TechnologyAdapter
import com.elthobhy.newsapp.ui.explore.detik.DetikAdapter
import com.elthobhy.newsapp.ui.explore.kapanlagi.KapanlagiAdapter
import com.elthobhy.newsapp.ui.explore.suara.SuaraAdapter
import com.elthobhy.newsapp.ui.explore.viva.VivaAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.business.FavoriteBusinessAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.detik.FavoriteDetikAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.entertainment.FavoriteEntertainmentAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.general.FavoriteGeneralAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.headline.FavoriteHeadlineAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.health.FavoriteHealthAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.kapanlagi.FavoriteKapanlagiAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.science.FavoriteScienceAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.sports.FavoriteSportsAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.suara.FavoriteSuaraAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.technology.FavoriteTechnologyAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.viva.FavoriteVivaAdapter
import com.elthobhy.newsapp.ui.home.HeadlineAdapter
import com.elthobhy.newsapp.utils.AppExecutors
import com.elthobhy.newsapp.viewmodel.business.BusinessViewModel
import com.elthobhy.newsapp.viewmodel.detail.DetailViewModel
import com.elthobhy.newsapp.viewmodel.detik.DetikViewModel
import com.elthobhy.newsapp.viewmodel.entertainment.EntertainmentViewModel
import com.elthobhy.newsapp.viewmodel.favorite.FavoriteViewModel
import com.elthobhy.newsapp.viewmodel.general.GeneralViewModel
import com.elthobhy.newsapp.viewmodel.headline.HeadlineViewModel
import com.elthobhy.newsapp.viewmodel.health.HealthViewModel
import com.elthobhy.newsapp.viewmodel.kapanlagi.KapanlagiViewModel
import com.elthobhy.newsapp.viewmodel.science.ScienceViewModel
import com.elthobhy.newsapp.viewmodel.sports.SportViewModel
import com.elthobhy.newsapp.viewmodel.suara.SuaraViewModel
import com.elthobhy.newsapp.viewmodel.technology.TechnologyViewModel
import com.elthobhy.newsapp.viewmodel.viva.VivaViewModel
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
    single { RemoteData.getInstance() }
    single { LocalData.getInstace(get()) }
    single { CatalogNewsRepository.getInstance(get(),get(),get()) }
    single { AppExecutors() }
}

val viewModelModule = module{
    single { HeadlineViewModel(get()) }
    single { BusinessViewModel(get()) }
    single { EntertainmentViewModel(get()) }
    single{ GeneralViewModel(get()) }
    single{ HealthViewModel(get()) }
    single{ ScienceViewModel(get()) }
    single{ SportViewModel(get()) }
    single{ TechnologyViewModel(get()) }
    single { DetikViewModel(get()) }
    single { VivaViewModel(get()) }
    single { SuaraViewModel(get()) }
    single { KapanlagiViewModel(get()) }
    single { DetailViewModel(get()) }
    single { FavoriteViewModel(get()) }
}
val adapterModule = module {
    factory { HeadlineAdapter() }
    factory { BusinessAdapter() }
    factory { EntertainmentAdapter() }
    factory { GeneralAdapter() }
    factory { HealthAdapter() }
    factory { ScienceAdapter() }
    factory { SportsAdapter() }
    factory { TechnologyAdapter() }
    factory { DetikAdapter() }
    factory { VivaAdapter() }
    factory { KapanlagiAdapter() }
    factory { SuaraAdapter() }

    factory { FavoriteHeadlineAdapter() }
    factory { FavoriteBusinessAdapter() }
    factory { FavoriteEntertainmentAdapter() }
    factory { FavoriteGeneralAdapter() }
    factory { FavoriteHealthAdapter() }
    factory { FavoriteScienceAdapter() }
    factory { FavoriteSportsAdapter() }
    factory { FavoriteTechnologyAdapter() }
    factory { FavoriteDetikAdapter() }
    factory { FavoriteVivaAdapter() }
    factory { FavoriteKapanlagiAdapter() }
    factory { FavoriteSuaraAdapter() }
}
