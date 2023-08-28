package com.elthobhy.newsapp.viewmodel.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.core.domain.model.Domain
import com.elthobhy.core.domain.usecase.UseCase

class DetailViewModel(private val useCase: UseCase) :
    ViewModel() {
//    fun setBookmarkedHeadline(article: Domain) {
//        val newState = !article.bookmarked
//        catalogRepo.setFavoriteHeadline(article, newState)
//    }

    fun getHeadline(content: String): LiveData<Domain> =
        useCase.getDetail(content)
}