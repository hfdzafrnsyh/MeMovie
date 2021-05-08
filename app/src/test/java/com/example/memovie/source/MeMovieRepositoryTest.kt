package com.example.memovie.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import org.junit.Assert.*
import com.example.memovie.dummy.DataDummy
import com.example.memovie.entity.MovieDetailEntity
import com.example.memovie.entity.MovieEntity
import com.example.memovie.entity.TvShowDetailEntity
import com.example.memovie.entity.TvShowEntity
import com.example.memovie.source.local.LocalDataSource
import com.example.memovie.source.remote.MeMovieDao
import com.example.memovie.source.remote.RemoteDataSource
import com.example.memovie.utils.AppExecutors
import com.example.memovie.utils.LiveDataTestUtils
import com.example.memovie.utils.PagedListUtils
import com.example.memovie.vo.Resource
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


class MeMovieRepositoryTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val movieResponse = DataDummy.generateDummyMovieRemote()
    private val movieId = movieResponse[0].id
    private val tvShowResponse = DataDummy.generateDummyTvshowRemote()
    private val tvShowId = tvShowResponse[0].id

    private val local = mock(LocalDataSource::class.java)
    private val appExecutors : AppExecutors =  AppExecutors(AppExecutors.TestExecutor(),AppExecutors.TestExecutor(),AppExecutors.TestExecutor() )

    private val meMovieRepository = FakeMeMovieRepository(remote , local , appExecutors)



    @Test
    fun getAllMovie() {

        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovie()).thenReturn(dataSourceFactory)
        meMovieRepository.getAllMovie()

        val movieEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyMovie()))
        verify(local).getAllMovie()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }


    @Test
    fun getAllTvShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShow()).thenReturn(dataSourceFactory)
        meMovieRepository.getAllTvShow()

        val tvShowEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyTvShow()))
        verify(local).getAllTvShow()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie() {
        val dummyMovie = MutableLiveData<MovieDetailEntity>()
        dummyMovie.value = DataDummy.generateDetailDummyMovie(DataDummy.generateDummyMovie()[0])
        `when`(local.getDetailMovie(movieId)).thenReturn(dummyMovie)

        val movieEntities = LiveDataTestUtils.getValue(meMovieRepository.getDetailMovie(movieId))
        verify(local).getDetailMovie(movieId)
        assertNotNull(movieEntities.data)
        assertNotNull(movieEntities.data?.mMovie?.title)
        assertEquals(movieResponse[0].title, movieEntities.data?.mMovie?.title)
    }

    @Test
    fun getDetailTvShow() {
        val dummyTvShow = MutableLiveData<TvShowDetailEntity>()
        dummyTvShow.value = DataDummy.generateDetailDummyTvShow(DataDummy.generateDummyTvShow()[0])
        `when`(local.getDetailTvShow(tvShowId)).thenReturn(dummyTvShow)

        val tvShowEntities = LiveDataTestUtils.getValue(meMovieRepository.getDetailTvShow(tvShowId))
        verify(local).getDetailTvShow(tvShowId)
        assertNotNull(tvShowEntities.data)
        assertNotNull(tvShowEntities.data?.mTvShow?.title)
        assertEquals(tvShowResponse[0].title, tvShowEntities.data?.mTvShow?.title)
    }

    @Test
    fun getMovieFavorite() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovieFavorite()).thenReturn(dataSourceFactory)
        meMovieRepository.getMovieFavorite()

        val movieEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyMovie()))
        verify(local).getMovieFavorite()
        assertNotNull(movieEntities)
        assertEquals(movieResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }


    @Test
    fun getTvShowFavorite() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getTvShowFavorite()).thenReturn(dataSourceFactory)
        meMovieRepository.getTvShowFavorite()

        val tvShowEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyTvShow()))
        verify(local).getTvShowFavorite()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntities.data?.size?.toLong())
    }


    @Test
    fun setMovieFavorite() {
        val movie = DataDummy.generateDummyMovie()[0].copy(favorite = false)
        doNothing().`when`(local).setMovieFavorite(movie, true)
        meMovieRepository.setMovieFavorite(movie, true)

        verify(local, times(1)).setMovieFavorite(movie, true)
    }

    @Test
    fun setTvShowFavorite() {
        val tvShow = DataDummy.generateDummyTvShow()[0].copy(favorite = false)
        doNothing().`when`(local).setTvShowFavorite(tvShow , true)
        meMovieRepository.setTvShowFavorite(tvShow,true)

        verify(local , times(1)).setTvShowFavorite(tvShow,true)
    }


}