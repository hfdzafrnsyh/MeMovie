package com.example.memovie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.example.memovie.dummy.DataDummy
import com.example.memovie.entity.MovieDetailEntity
import com.example.memovie.entity.MovieEntity
import com.example.memovie.source.MeMovieRepository
import com.example.memovie.vo.Resource
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private lateinit var movieViewModel: MovieViewModel

    @Mock
    private lateinit var meMovieRepository: MeMovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var favObserver: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList : PagedList<MovieEntity>

    @Before
    fun setUp(){
        movieViewModel = MovieViewModel(meMovieRepository)
    }


    @Test
    fun getMovie() {

        val dummyMovie = Resource.success(pagedList)
        `when`(dummyMovie.data?.size).thenReturn(10)
        val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movie.value = dummyMovie

        `when`(meMovieRepository.getAllMovie()).thenReturn(movie)

        val movieEntities = movieViewModel.getMovie().value?.data
        verify<MeMovieRepository>(meMovieRepository).getAllMovie()

        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        movieViewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }


    @Test
    fun getMovieFavoriteSuccess(){
        val expected = MutableLiveData<PagedList<MovieEntity>>()
        expected.value = PagedTestDataSource.snapshot(DataDummy.generateDummyMovie())

        `when`(meMovieRepository.getMovieFavorite()).thenReturn(expected)

        movieViewModel.getMovieFavorite().observeForever(favObserver)
        verify(favObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = movieViewModel.getMovieFavorite().value
        assertEquals(expectedValue,actualValue)
        assertEquals(expectedValue?.snapshot(),actualValue?.snapshot())
        assertEquals(expectedValue?.size,actualValue?.size)
    }

    @Test
    fun getMovieFavoriteIsEmpty(){
        val expected = MutableLiveData<PagedList<MovieEntity>>()
        expected.value = PagedTestDataSource.snapshot()

        `when`(meMovieRepository.getMovieFavorite()).thenReturn(expected)

        movieViewModel.getMovieFavorite().observeForever(favObserver)
        verify(favObserver).onChanged(expected.value)

        val actualValueDataSize = movieViewModel.getMovieFavorite().value?.size
        Assert.assertTrue("Size data Should be  0 , actual is ${actualValueDataSize}" , actualValueDataSize == 0)
    }


    @Test
    fun setMovieFavorite() {
        val dummyMovie = DataDummy.generateDummyMovie()[0]
        doNothing().`when`(meMovieRepository).setMovieFavorite(dummyMovie , true)
        movieViewModel.setMovieFavorite(dummyMovie)
        verify(meMovieRepository , times(1)).setMovieFavorite(dummyMovie , true)
    }

    class PagedTestDataSource private constructor(private val movie : List<MovieEntity>) : PositionalDataSource<MovieEntity>(){

        companion object{
            fun snapshot(movie : List<MovieEntity> = listOf()) : PagedList<MovieEntity>{
                return PagedList.Builder(PagedTestDataSource(movie), 10)
                        .setNotifyExecutor(Executors.newSingleThreadExecutor())
                        .setFetchExecutor(Executors.newSingleThreadExecutor())
                        .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<MovieEntity>) {
            callback.onResult(movie , 0 , movie.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MovieEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(movie.subList(start, end))
        }

    }
}