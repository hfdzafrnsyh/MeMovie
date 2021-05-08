package com.example.memovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.memovie.entity.*
import com.example.memovie.source.MeMovieRepository
import com.example.memovie.vo.Resource

class DetailViewModel(private val meMovieRepository: MeMovieRepository) : ViewModel() {

    var movieId = MutableLiveData<String>()
    var tvShowId = MutableLiveData<String>()


    fun setMovie(movieId : String){
        this.movieId.value = movieId
    }

    fun setTvShow(tvShowId : String){
        this.tvShowId.value = tvShowId
    }



   var getDataMovie  : LiveData<Resource<MovieDetailEntity>> = Transformations.switchMap(movieId){ mMovieId ->
       meMovieRepository.getDetailMovie(mMovieId)
   }


    var getDataTvShow : LiveData<Resource<TvShowDetailEntity>> = Transformations.switchMap(tvShowId) {mTvShowId ->
        meMovieRepository.getDetailTvShow(mTvShowId)
    }


    fun setMovieFavorite(){
        val dataMovie = getDataMovie.value
        if(dataMovie != null){
            val movie = dataMovie.data

            if(movie != null ){
                val movieEntity = movie.mMovie
                val newState = !movieEntity.favorite
                meMovieRepository.setMovieFavorite(movieEntity, newState)
            }
        }
    }


    fun setTvShowFavorite(){
        val dataTvShow = getDataTvShow.value
        if(dataTvShow != null){
            val tvShow = dataTvShow.data

            if(tvShow != null ){
                val tvShowEntity = tvShow.mTvShow
                val newState = !tvShowEntity.favorite
                meMovieRepository.setTvShowFavorite(tvShowEntity, newState)
            }
        }
    }




}