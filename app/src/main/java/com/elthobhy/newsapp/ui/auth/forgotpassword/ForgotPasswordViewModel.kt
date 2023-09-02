package com.elthobhy.newsapp.ui.auth.forgotpassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.core.domain.usecase.UseCase
import com.elthobhy.core.utils.vo.Resource

class ForgotPasswordViewModel(private val userCase: UseCase): ViewModel() {
    fun forgotPassword(email: String): LiveData<Resource<Void>> = userCase.forgotPassword(email)
}