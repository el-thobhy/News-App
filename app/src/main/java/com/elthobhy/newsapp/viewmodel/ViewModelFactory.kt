package com.elthobhy.newsapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.di.Injection
import com.elthobhy.newsapp.viewmodel.business.BusinessViewModel
import com.elthobhy.newsapp.viewmodel.detail.DetailViewModel
import com.elthobhy.newsapp.viewmodel.detik.DetikViewModel
import com.elthobhy.newsapp.viewmodel.entertainment.EntertainmentViewModel
import com.elthobhy.newsapp.viewmodel.favorite.FavoriteViewModel
import com.elthobhy.newsapp.viewmodel.general.GeneralViewModel
import com.elthobhy.newsapp.viewmodel.headline.HeadlineViewModel
import com.elthobhy.newsapp.viewmodel.health.HealthViewModel
import com.elthobhy.newsapp.viewmodel.kapanlagi.KapanlagiViewModel
import com.elthobhy.newsapp.viewmodel.science.ScienceViewModel
import com.elthobhy.newsapp.viewmodel.sports.SportViewModel
import com.elthobhy.newsapp.viewmodel.suara.SuaraViewModel
import com.elthobhy.newsapp.viewmodel.technology.TechnologyViewModel
import com.elthobhy.newsapp.viewmodel.viva.VivaViewModel

class ViewModelFactory private constructor(private val catalogRepo: CatalogNewsRepository):
ViewModelProvider.NewInstanceFactory(){
    companion object{
        @Volatile
        private var instance : ViewModelFactory? = null

        fun getInstance(context:Context): ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideCatalogRepository(context)).apply {
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
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) ->{
                FavoriteViewModel(catalogRepo) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) ->{
                DetailViewModel(catalogRepo) as T
            }
            else->{
                throw Throwable("Uknown ViewModel class: "+ modelClass.name)
            }
        }
    }
}