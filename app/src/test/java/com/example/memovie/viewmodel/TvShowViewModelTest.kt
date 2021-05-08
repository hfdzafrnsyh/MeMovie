package com.example.memovie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.example.memovie.dummy.DataDummy
import com.example.memovie.entity.TvShowEntity
import com.example.memovie.source.MeMovieRepository
import com.example.memovie.vo.Resource
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.times
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
class TvShowViewModelTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var tvShowViewModel: TvShowViewModel

    @Mock
    private lateinit var meMovieRepository : MeMovieRepository

    @Mock
    private lateinit var observer : Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var favObserver : Observer<PagedList<TvShowEntity>>


    @Mock
    private lateinit var pagedList : PagedList<TvShowEntity>

    @Before
    fun setUp(){
        tvShowViewModel = TvShowViewModel(meMovieRepository)
    }


    @Test
    fun getTvShow() {

        val dummyTvShow = Resource.success(pagedList)
        `when`(dummyTvShow.data?.size).thenReturn(10)
        val tvShow = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShow.value = dummyTvShow

        `when`(meMovieRepository.getAllTvShow()).thenReturn(tvShow)

        val tvShowEntities = tvShowViewModel.getTvShow().value?.data

        verify<MeMovieRepository>(meMovieRepository).getAllTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities?.size)

        tvShowViewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }



    @Test
    fun getTvShowFavoriteSuccess(){
        val expected = MutableLiveData<PagedList<TvShowEntity>>()
        expected.value = PagedTestDataSource.snapshot(DataDummy.generateDummyTvShow())

        `when`(meMovieRepository.getTvShowFavorite()).thenReturn(expected)

        tvShowViewModel.getTvShowFavorite().observeForever(favObserver)
        verify(favObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = tvShowViewModel.getTvShowFavorite().value
        assertEquals(expectedValue,actualValue)
        assertEquals(expectedValue?.snapshot(),actualValue?.snapshot())
        assertEquals(expectedValue?.size,actualValue?.size)
    }

    @Test
    fun getTvShowFavoriteIsEmpty(){
        val expected = MutableLiveData<PagedList<TvShowEntity>>()
        expected.value = PagedTestDataSource.snapshot()

        `when`(meMovieRepository.getTvShowFavorite()).thenReturn(expected)

        tvShowViewModel.getTvShowFavorite().observeForever(favObserver)
        verify(favObserver).onChanged(expected.value)

        val actualValueDataSize = tvShowViewModel.getTvShowFavorite().value?.size
        Assert.assertTrue("Size data Should be  0 , actual is ${actualValueDataSize}" , actualValueDataSize == 0)
    }


    @Test
    fun setTvShowFavorite(){
        val dummyTvShow = DataDummy.generateDummyTvShow()[0]
        doNothing().`when`(meMovieRepository).setTvShowFavorite(dummyTvShow , true)
        tvShowViewModel.setTvShowFavorite(dummyTvShow)
        verify(meMovieRepository , times(1)).setTvShowFavorite(dummyTvShow , true)
    }


    class PagedTestDataSource private constructor(private val tvShow : List<TvShowEntity>) : PositionalDataSource<TvShowEntity>(){

        companion object{
            fun snapshot(tvShow : List<TvShowEntity> = listOf()) : PagedList<TvShowEntity>{
                return PagedList.Builder(PagedTestDataSource(tvShow), 10)
                        .setNotifyExecutor(Executors.newSingleThreadExecutor())
                        .setFetchExecutor(Executors.newSingleThreadExecutor())
                        .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<TvShowEntity>) {
            callback.onResult(tvShow , 0 , tvShow.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TvShowEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(tvShow.subList(start, end))
        }

    }


}