package com.example.memovie.utils.di

import android.content.Context
import com.example.memovie.source.MeMovieRepository
import com.example.memovie.source.local.LocalDataSource
import com.example.memovie.source.remote.MeMovieDatabase
import com.example.memovie.source.remote.RemoteDataSource
import com.example.memovie.utils.AppExecutors
import com.example.memovie.utils.JsonHelper

object Injection {

    fun provideRepository(context: Context) : MeMovieRepository {

        val database = MeMovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.meMovieDao())
        val appExecutors = AppExecutors()

        return MeMovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

}