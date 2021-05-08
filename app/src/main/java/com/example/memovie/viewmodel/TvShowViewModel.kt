package com.example.memovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.memovie.entity.TvShowEntity
import com.example.memovie.source.MeMovieRepository
import com.example.memovie.vo.Resource

class TvShowViewModel(private val meMovieRepository: MeMovieRepository) : ViewModel() {

    fun getTvShow() : LiveData<Resource<PagedList<TvShowEntity>>> = meMovieRepository.getAllTvShow()

    fun getTvShowFavorite() : LiveData<PagedList<TvShowEntity>> =
            meMovieRepository.getTvShowFavorite()

    fun setTvShowFavorite(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.favorite
        meMovieRepository.setTvShowFavorite(tvShowEntity , newState)
    }
}