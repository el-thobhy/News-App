package com.elthobhy.newsapp.viewmodel.favorite

import androidx.lifecycle.ViewModel
import com.elthobhy.core.data.source.CatalogNewsRepository

class FavoriteViewModel(private val catalogRepo: CatalogNewsRepository) : ViewModel() {
//    fun getFavoriteHeadline(): LiveData<List<Domain>> = catalogRepo.getFavoritesHeadline()
}