package com.example.memovie.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.memovie.entity.MovieDetailEntity
import com.example.memovie.entity.MovieEntity
import com.example.memovie.entity.TvShowDetailEntity
import com.example.memovie.entity.TvShowEntity
import com.example.memovie.source.local.LocalDataSource
import com.example.memovie.source.remote.RemoteDataSource
import com.example.memovie.source.remote.response.ApiResponse
import com.example.memovie.source.remote.response.DataResponse
import com.example.memovie.utils.AppExecutors
import com.example.memovie.vo.Resource

class FakeMeMovieRepository(private val remoteDataSource: RemoteDataSource,
                            private val localDataSource : LocalDataSource,
                            private val appExecutors : AppExecutors ) : MeMovieSource {



    override fun getAllMovie(): LiveData<Resource<PagedList<MovieEntity>>> {

        return object : NetworkBoundResource<PagedList<MovieEntity>, List<DataResponse>>(appExecutors){
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                return LivePagedListBuilder(localDataSource.getAllMovie(), config).build()
            }



            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                    data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<DataResponse>>> =
                    remoteDataSource.getAllMovie()

            override fun saveCallResult(dataResponse: List<DataResponse>) {
                val movieList = ArrayList<MovieEntity>()

                for(response in dataResponse){
                    val movie = MovieEntity(
                            response.id,
                            response.title,
                            response.description,
                            response.category,
                            response.poster,
                            false,
                            response.date)

                    movieList.add(movie)

                }

                localDataSource.insertMovie(movieList)
            }

        }.asLiveData()

    }



    override fun getDetailMovie(movieId : String) : LiveData<Resource<MovieDetailEntity>>{

        return object : NetworkBoundResource<MovieDetailEntity , DataResponse>(appExecutors) {

            override fun loadFromDB(): LiveData<MovieDetailEntity> =
                    localDataSource.getDetailMovie(movieId)


            override fun shouldFetch(data: MovieDetailEntity?): Boolean =
                    data == null

            override fun createCall(): LiveData<ApiResponse<DataResponse>> =
                    remoteDataSource.getDetailMovie(movieId)

            override fun saveCallResult(dataResponse: DataResponse){
                var movie = dataResponse.id
                localDataSource.getDetailMovie(movie)
            }

        }.asLiveData()


    }




    override fun getAllTvShow() : LiveData<Resource<PagedList<TvShowEntity>>>{

        return object : NetworkBoundResource<PagedList<TvShowEntity> , List<DataResponse>>(appExecutors) {

            public override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                return LivePagedListBuilder(localDataSource.getAllTvShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                    data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<DataResponse>>> =
                    remoteDataSource.getAllTvShow()

            override fun saveCallResult(dataResponse: List<DataResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()

                for(response in dataResponse){
                    val tvShow = TvShowEntity(
                            response.id,
                            response.title,
                            response.description,
                            response.category,
                            response.poster,
                            false,
                            response.date)

                    tvShowList.add(tvShow)
                }

                localDataSource.insertTvShow(tvShowList)
            }

        }.asLiveData()


    }




    override fun getDetailTvShow(tvShowId : String) : LiveData<Resource<TvShowDetailEntity>> {

        return object : NetworkBoundResource<TvShowDetailEntity , DataResponse>(appExecutors) {

            override fun loadFromDB(): LiveData<TvShowDetailEntity> =
                    localDataSource.getDetailTvShow(tvShowId)

            override fun shouldFetch(data: TvShowDetailEntity?): Boolean =
                    data == null

            override fun createCall(): LiveData<ApiResponse<DataResponse>> =
                    remoteDataSource.getDetailTvShow(tvShowId)

            override fun saveCallResult(dataResponse: DataResponse){
                val tvShow = dataResponse.id
                localDataSource.getDetailTvShow(tvShow)
            }

        }.asLiveData()

    }



   override fun setMovieFavorite(movie: MovieEntity, state: Boolean) {
       appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movie, state) }
   }

   override fun getMovieFavorite(): LiveData<PagedList<MovieEntity>>{
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
        return LivePagedListBuilder(localDataSource.getMovieFavorite(), config).build()
    }


  override  fun setTvShowFavorite(tvShow : TvShowEntity , state : Boolean){
        appExecutors.diskIO().execute{ localDataSource.setTvShowFavorite(tvShow,state) }
    }

   override fun getTvShowFavorite(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
        return LivePagedListBuilder(localDataSource.getTvShowFavorite(), config).build()
    }



}