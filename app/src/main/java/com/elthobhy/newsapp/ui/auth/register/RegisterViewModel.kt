package com.elthobhy.newsapp.ui.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.core.domain.usecase.UseCase
import com.elthobhy.core.utils.vo.Resource
import com.google.firebase.auth.AuthResult

class RegisterViewModel(private val useCase: UseCase) : ViewModel() {
    fun register(name: String, email: String, password: String): LiveData<Resource<AuthResult>> =
        useCase.getDataRegister(name, email, password)
}