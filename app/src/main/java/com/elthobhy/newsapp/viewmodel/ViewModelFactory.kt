package com.elthobhy.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.di.Injection

class ViewModelFactory private constructor(private val catalogRepo: CatalogNewsRepository):
ViewModelProvider.NewInstanceFactory(){
    companion object{
        @Volatile
        private var instance : ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideCatalogRepository()).apply {
                    instance = this
                }
            }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HeadlineViewModel::class.java) ->{
                HeadlineViewModel(catalogRepo) as T
            }
            modelClass.isAssignableFrom(DetikViewModel::class.java) ->{
                DetikViewModel(catalogRepo) as T
            }else->{
                throw Throwable("Uknown ViewModel class: "+ modelClass.name)
            }
        }
    }
}