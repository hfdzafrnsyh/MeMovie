package com.example.memovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.memovie.entity.MovieDetailEntity
import com.example.memovie.entity.MovieEntity
import com.example.memovie.source.MeMovieRepository
import com.example.memovie.vo.Resource

class MovieViewModel(private val meMovieRepository: MeMovieRepository) : ViewModel() {

    fun getMovie() : LiveData<Resource<PagedList<MovieEntity>>> = meMovieRepository.getAllMovie()

    fun getMovieFavorite() : LiveData<PagedList<MovieEntity>> =
           meMovieRepository.getMovieFavorite()


    fun setMovieFavorite(movieEntity: MovieEntity) {
        val newState = !movieEntity.favorite
        meMovieRepository.setMovieFavorite(movieEntity, newState)
    }



}