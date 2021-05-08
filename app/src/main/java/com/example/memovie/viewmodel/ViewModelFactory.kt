package com.example.memovie.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.memovie.source.MeMovieRepository
import com.example.memovie.utils.di.Injection

class ViewModelFactory private constructor(private val mMeMovieRepository: MeMovieRepository) : ViewModelProvider.NewInstanceFactory(){

    companion object{
        @Volatile


        private var instance : ViewModelFactory? = null

        fun getInstance(context: Context) : ViewModelFactory =
                instance ?: synchronized(this){
                    instance ?:  ViewModelFactory(Injection.provideRepository(context))
                }
    }

    @Suppress("UNCHECKED_CAST")
     override fun <T : ViewModel> create(modelClass : Class<T>) : T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(mMeMovieRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(mMeMovieRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                return TvShowViewModel(mMeMovieRepository) as T
            }


            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

}