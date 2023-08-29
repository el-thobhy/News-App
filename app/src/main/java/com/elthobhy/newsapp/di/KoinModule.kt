package com.elthobhy.newsapp.di

import com.elthobhy.core.domain.usecase.RepositoryInteract
import com.elthobhy.core.domain.usecase.UseCase
import com.elthobhy.newsapp.ui.category.technology.TechnologyAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.headline.FavoriteHeadlineAdapter
import com.elthobhy.newsapp.ui.home.HeadlineAdapter
import com.elthobhy.newsapp.viewmodel.detail.DetailViewModel
import com.elthobhy.newsapp.viewmodel.favorite.FavoriteViewModel
import com.elthobhy.newsapp.viewmodel.headline.HeadlineViewModel
import com.elthobhy.newsapp.viewmodel.indonesianews.IndonesiaNewsViewModel
import com.elthobhy.newsapp.viewmodel.search.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module{
    single { HeadlineViewModel(get()) }
    single { DetailViewModel(get()) }
    single { FavoriteViewModel(get()) }
    single { SearchViewModel(get()) }
    single { IndonesiaNewsViewModel(get()) }
}

val useCase = module {
    factory<UseCase> { RepositoryInteract(get()) }
}

val adapterModule = module {
    factory { HeadlineAdapter() }
    factory { TechnologyAdapter() }
    factory { FavoriteHeadlineAdapter() }
}
