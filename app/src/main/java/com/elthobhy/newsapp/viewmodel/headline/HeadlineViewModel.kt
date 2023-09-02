package com.elthobhy.newsapp.viewmodel.headline

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.elthobhy.core.domain.model.Domain
import com.elthobhy.core.domain.model.User
import com.elthobhy.core.domain.usecase.UseCase
import com.elthobhy.core.utils.vo.Resource

class HeadlineViewModel(private val useCase: UseCase): ViewModel() {

    fun getHeadline(): LiveData<Resource<List<Domain>>> =
        useCase.getHeadline().asLiveData()

    fun getDataUser(uid: String): LiveData<Resource<User>> = useCase.getDataUser(uid)
}