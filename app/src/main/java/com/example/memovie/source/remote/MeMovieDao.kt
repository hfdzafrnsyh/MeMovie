package com.example.memovie.source.remote

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.memovie.entity.*



@Dao
interface MeMovieDao {

    @Query("SELECT * FROM movieentities")
    fun getMovie() : DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvShowentities")
    fun getTvShow() : DataSource.Factory<Int , TvShowEntity>


    @Query("SELECT * FROM movieentities WHERE id = :movieId")
    fun getDetailMovie(movieId: String) : LiveData<MovieDetailEntity>


    @Query("SELECT * FROM tvShowentities WHERE id = :tvShowId")
    fun getDetailTvShow(tvShowId: String) : LiveData<TvShowDetailEntity>


    @Query("SELECT * FROM movieentities WHERE favorite = 1")
    fun getFavoriteMovie() : DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvShowentities WHERE favorite = 1")
    fun getTvShowFavorite() :  DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie : List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow : List<TvShowEntity>)

    @Update
    fun updateMovie(movie : MovieEntity)

    @Update
    fun updateTvShow(tvShow : TvShowEntity)


}