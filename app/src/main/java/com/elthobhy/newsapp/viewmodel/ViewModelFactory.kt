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
            }
            modelClass.isAssignableFrom(VivaViewModel::class.java) ->{
                VivaViewModel(catalogRepo) as T
            }
            modelClass.isAssignableFrom(KapanlagiViewModel::class.java) ->{
                KapanlagiViewModel(catalogRepo) as T
            }
            modelClass.isAssignableFrom(SuaraViewModel::class.java) ->{
                SuaraViewModel(catalogRepo) as T
            }
            modelClass.isAssignableFrom(BusinessViewModel::class.java) ->{
                BusinessViewModel(catalogRepo) as T
            }
            modelClass.isAssignableFrom(EntertainmentViewModel::class.java) ->{
                EntertainmentViewModel(catalogRepo) as T
            }
            modelClass.isAssignableFrom(GeneralViewModel::class.java) ->{
                GeneralViewModel(catalogRepo) as T
            }
            modelClass.isAssignableFrom(HealthViewModel::class.java) ->{
                HealthViewModel(catalogRepo) as T
            }
            modelClass.isAssignableFrom(ScienceViewModel::class.java) ->{
                ScienceViewModel(catalogRepo) as T
            }
            modelClass.isAssignableFrom(SportViewModel::class.java) ->{
                SportViewModel(catalogRepo) as T
            }
            modelClass.isAssignableFrom(TechnologyViewModel::class.java) ->{
                TechnologyViewModel(catalogRepo) as T
            }
            else->{
                throw Throwable("Uknown ViewModel class: "+ modelClass.name)
            }
        }
    }
}