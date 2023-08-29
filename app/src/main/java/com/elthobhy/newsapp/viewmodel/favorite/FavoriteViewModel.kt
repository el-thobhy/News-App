package com.elthobhy.newsapp.viewmodel.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.elthobhy.core.domain.model.Domain
import com.elthobhy.core.domain.usecase.UseCase

class FavoriteViewModel(private val catalogRepo: UseCase) : ViewModel() {
    fun getFavoriteHeadline(): LiveData<List<Domain>> = catalogRepo.getFavorite().asLiveData()
}