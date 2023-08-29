package com.elthobhy.newsapp.viewmodel.indonesianews

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.elthobhy.core.domain.model.Domain
import com.elthobhy.core.domain.usecase.UseCase
import com.elthobhy.core.utils.vo.Resource

class IndonesiaNewsViewModel(private val useCase: UseCase) : ViewModel() {
    fun getIndonesianNews(
        news: String,
        detik: Boolean,
        suara: Boolean,
        kapanlagi: Boolean,
        liputan: Boolean
    ): LiveData<Resource<List<Domain>>> =
        useCase.getIndonesiaNews(news, detik, suara, kapanlagi, liputan).asLiveData()

}