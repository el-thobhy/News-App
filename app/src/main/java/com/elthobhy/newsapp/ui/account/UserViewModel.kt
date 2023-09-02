package com.elthobhy.newsapp.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.core.domain.model.User
import com.elthobhy.core.domain.usecase.UseCase
import com.elthobhy.core.utils.vo.Resource

class UserViewModel(private val userCase: UseCase) : ViewModel() {
    fun getDataUser(uid: String): LiveData<Resource<User>> = userCase.getDataUser(uid)
}