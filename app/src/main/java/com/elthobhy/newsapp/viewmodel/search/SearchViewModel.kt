package com.elthobhy.newsapp.viewmodel.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.elthobhy.core.domain.usecase.UseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest

@FlowPreview
@ExperimentalCoroutinesApi
class SearchViewModel(private val useCase: UseCase): ViewModel() {
    val queryChannel = MutableStateFlow("")
    val searchResult = queryChannel
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .flatMapLatest {
            useCase.getSearch(it)
        }.asLiveData()
}