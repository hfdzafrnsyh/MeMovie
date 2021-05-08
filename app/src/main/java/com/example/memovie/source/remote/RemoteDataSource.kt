package com.example.memovie.source.remote

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.memovie.source.remote.response.ApiResponse
import com.example.memovie.source.remote.response.DataResponse
import com.example.memovie.utils.JsonHelper
import com.example.memovie.utils.IdlingResource

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler()


    companion object {

        private const val SERVICE_LATENCY_IN_MILLIS : Long = 2000

        @Volatile
        private var instance : RemoteDataSource? = null

        fun getInstance(helper: JsonHelper) : RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource(helper)
                }
    }

    fun getAllMovie() : LiveData<ApiResponse<List<DataResponse>>> {
        IdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<DataResponse>>>()
        handler.postDelayed({
            resultMovie.value = ApiResponse.success(jsonHelper.loadMovie())
            IdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)

        return resultMovie
    }

    fun getAllTvShow() : LiveData<ApiResponse<List<DataResponse>>>{
        IdlingResource.increment()
        val resultTvShow = MutableLiveData<ApiResponse<List<DataResponse>>>()
        handler.postDelayed({
            resultTvShow.value = ApiResponse.success(jsonHelper.loadTvShow())
            IdlingResource.decrement()
        } , SERVICE_LATENCY_IN_MILLIS)

        return resultTvShow
    }

    fun getDetailMovie(movieId : String) : LiveData<ApiResponse<DataResponse>>{
        IdlingResource.increment()
        val resultMovieById = MutableLiveData<ApiResponse<DataResponse>>()
        handler.postDelayed({
            resultMovieById.value = ApiResponse.success(jsonHelper.loadDetailMovie(movieId))
            IdlingResource.decrement()
        } , SERVICE_LATENCY_IN_MILLIS)
        return resultMovieById
    }

    fun getDetailTvShow(tvShowId : String) : LiveData<ApiResponse<DataResponse>>{
        IdlingResource.increment()
        val resultTvShowById = MutableLiveData<ApiResponse<DataResponse>>()
        handler.postDelayed({
            resultTvShowById.value =ApiResponse.success(jsonHelper.loadDetailTvShow(tvShowId))
        } , SERVICE_LATENCY_IN_MILLIS)
        return resultTvShowById
    }

}