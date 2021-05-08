package com.example.memovie.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.memovie.entity.MovieDetailEntity
import com.example.memovie.entity.TvShowDetailEntity
import com.example.memovie.entity.MovieEntity
import com.example.memovie.entity.TvShowEntity
import com.example.memovie.source.remote.MeMovieDao

class LocalDataSource private constructor(private val mMeMovieDao : MeMovieDao){

    companion object {
        private var INSTANCE : LocalDataSource? = null

        fun getInstance(meMovieDao: MeMovieDao) : LocalDataSource =
                INSTANCE ?: LocalDataSource(meMovieDao)
    }


    fun getAllMovie() : DataSource.Factory<Int, MovieEntity> = mMeMovieDao.getMovie()

    fun getAllTvShow() : DataSource.Factory<Int, TvShowEntity> = mMeMovieDao.getTvShow()

    fun getDetailMovie(movieId : String) : LiveData<MovieDetailEntity> = mMeMovieDao.getDetailMovie(movieId)

    fun getDetailTvShow(tvShowId : String) : LiveData<TvShowDetailEntity> = mMeMovieDao.getDetailTvShow(tvShowId)

    fun getMovieFavorite () : DataSource.Factory<Int, MovieEntity> = mMeMovieDao.getFavoriteMovie()

    fun getTvShowFavorite() : DataSource.Factory<Int, TvShowEntity> = mMeMovieDao.getTvShowFavorite()

    fun insertMovie(movie : List<MovieEntity>) = mMeMovieDao.insertMovie(movie)

    fun insertTvShow(tvShow : List<TvShowEntity>) = mMeMovieDao.insertTvShow(tvShow)


    fun setMovieFavorite(movie : MovieEntity , newState : Boolean ) {
        movie.favorite = newState
        mMeMovieDao.updateMovie(movie)
    }

    fun setTvShowFavorite(tvShow : TvShowEntity , newState : Boolean) {
        tvShow.favorite = newState
        mMeMovieDao.updateTvShow(tvShow)
    }




}