package com.example.memovie.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.memovie.entity.MovieDetailEntity
import com.example.memovie.entity.TvShowDetailEntity
import com.example.memovie.entity.MovieEntity
import com.example.memovie.entity.TvShowEntity
import com.example.memovie.vo.Resource


interface MeMovieSource {


    fun getAllMovie() : LiveData<Resource<PagedList<MovieEntity>>>

    fun getAllTvShow() : LiveData<Resource<PagedList<TvShowEntity>>>

    fun getDetailMovie(movieId : String) : LiveData<Resource<MovieDetailEntity>>

    fun getDetailTvShow(tvShowId : String) : LiveData<Resource<TvShowDetailEntity>>

    fun getMovieFavorite() : LiveData<PagedList<MovieEntity>>

    fun setMovieFavorite(movie: MovieEntity, state: Boolean)

    fun getTvShowFavorite() : LiveData<PagedList<TvShowEntity>>

    fun setTvShowFavorite(tvShowId : TvShowEntity , state : Boolean)

}