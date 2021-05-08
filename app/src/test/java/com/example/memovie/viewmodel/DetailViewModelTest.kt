package com.example.memovie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.memovie.dummy.DataDummy
import com.example.memovie.entity.MovieDetailEntity
import com.example.memovie.entity.MovieEntity
import com.example.memovie.entity.TvShowDetailEntity
import com.example.memovie.entity.TvShowEntity
import com.example.memovie.source.MeMovieRepository
import com.example.memovie.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

        private lateinit var detailViewModel : DetailViewModel
        private val dummyMovie = DataDummy.generateDummyMovie()[0]
        private val dummyTvShow = DataDummy.generateDummyTvShow()[0]
        private val movieId = dummyMovie.id
        private val tvShowId = dummyTvShow.id


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var meMovieRepository: MeMovieRepository

    @Mock
    private lateinit var movieObserver : Observer<Resource<MovieDetailEntity>>

    @Mock
    private lateinit var tvShowObserver : Observer<Resource<TvShowDetailEntity>>



    @Before
    fun setUp(){
        detailViewModel = DetailViewModel(meMovieRepository)
        detailViewModel.setTvShow(tvShowId)
        detailViewModel.setMovie(movieId)
    }



    @Test
    fun setMovie() {
        var movie = MutableLiveData<Resource<MovieDetailEntity>>()
        movie.value =  Resource.success(DataDummy.generateDetailDummyMovie(dummyMovie))

        `when`(meMovieRepository.getDetailMovie(movieId)).thenReturn(movie)

        detailViewModel.setMovie(movieId)
        detailViewModel.getDataMovie.observeForever(movieObserver)

        verify(movieObserver).onChanged(movie.value)

        val expectMovieValue = movie.value
        val actualValue = detailViewModel.getDataMovie.value

        assertEquals(expectMovieValue , actualValue)

    }

    @Test
    fun setTvShow() {
        var tvShow = MutableLiveData<Resource<TvShowDetailEntity>>()
        tvShow.value =  Resource.success(DataDummy.generateDetailDummyTvShow(dummyTvShow))

        `when`(meMovieRepository.getDetailTvShow(tvShowId)).thenReturn(tvShow)

        detailViewModel.setTvShow(tvShowId)
        detailViewModel.getDataTvShow.observeForever(tvShowObserver)

        verify(tvShowObserver).onChanged(tvShow.value)

        val expectTvShowValue = tvShow.value
        val actualValue = detailViewModel.getDataTvShow.value

        assertEquals(expectTvShowValue , actualValue)
    }

    @Test
    fun setMovieFavorite(){
        val movie = MutableLiveData<Resource<MovieDetailEntity>>()
        movie.value = Resource.success(DataDummy.generateDetailDummyMovie(dummyMovie))

        `when`(meMovieRepository.getDetailMovie(movieId)).thenReturn(movie)

        detailViewModel.getDataMovie = meMovieRepository.getDetailMovie(movieId)
        detailViewModel.setMovieFavorite()
        detailViewModel.getDataMovie.observeForever(movieObserver)

        verify(movieObserver).onChanged(movie.value)
        verify(meMovieRepository).setMovieFavorite(dummyMovie , true)

        val expectMovieValue = movie.value
        val actualValue = detailViewModel.getDataMovie.value

        assertEquals(expectMovieValue , actualValue)
        verifyNoMoreInteractions(movieObserver)
    }

    @Test
    fun setTvShowFavorite(){

        val tvShow = MutableLiveData<Resource<TvShowDetailEntity>>()
        tvShow.value = Resource.success(DataDummy.generateDetailDummyTvShow(dummyTvShow))

        `when`(meMovieRepository.getDetailTvShow(tvShowId)).thenReturn(tvShow)

        detailViewModel.getDataTvShow = meMovieRepository.getDetailTvShow(tvShowId)
        detailViewModel.setTvShowFavorite()
        detailViewModel.getDataTvShow.observeForever(tvShowObserver)

        verify(tvShowObserver).onChanged(tvShow.value)
        verify(meMovieRepository).setTvShowFavorite(dummyTvShow, true)

        val expectedTvShowValue = tvShow.value
        val actualValue = detailViewModel.getDataTvShow.value

        assertEquals(expectedTvShowValue , actualValue)
        verifyNoMoreInteractions(tvShowObserver)
    }

}




