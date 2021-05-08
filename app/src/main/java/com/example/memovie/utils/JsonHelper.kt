package com.example.memovie.utils

import android.content.Context
import com.example.memovie.source.remote.response.DataResponse
import org.json.JSONObject
import org.json.JSONException
import java.io.IOException
import kotlin.collections.ArrayList

class JsonHelper(private val context : Context) {



    private fun parsingFile(file : String) : String?{
        return try{
            val `is` = context.assets.open(file)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        }catch ( ex : IOException){
            ex.printStackTrace()
            null
        }
    }



    fun loadMovie() : List<DataResponse> {

        val listMovie = ArrayList<DataResponse>()
        var posterPath = "https://image.tmdb.org/t/p/w185"

        try{
            val responseObject = JSONObject(parsingFile("responsesMovie.json"))
            val listMovieArray = responseObject.getJSONArray("items")
            for ( i in 0 until listMovieArray.length()){

                val movie = listMovieArray.getJSONObject(i)

                val id = movie.getInt("id")
                val title = movie.getString("title")
                val description = movie.getString("overview")
                val category = movie.getJSONArray("genre")
                val poster = posterPath + movie.getString("poster_path")
                val date = movie.getString("release_date")

                lateinit var genre : String

                for(j in 0 until category.length()){
                    genre = category[j].toString()
                }

                val movieResponse = DataResponse(id.toString(),title,description,genre,poster,date)
                listMovie.add(movieResponse)

            }
        }catch ( e : JSONException){
            e.printStackTrace()
        }

        return listMovie
    }

    fun loadTvShow() : List<DataResponse>{

        val listTvShow = ArrayList<DataResponse>()


        var posterPath = "https://image.tmdb.org/t/p/w185"

        try {
            val responseObject = JSONObject(parsingFile("responsesTvshow.json"))
            val listTvShowArray = responseObject.getJSONArray("items")

            for(i in 0 until listTvShowArray.length()){
                val tvShowResult = listTvShowArray.getJSONObject(i)

                val id = tvShowResult.getInt("id")
                val title = tvShowResult.getString("title")
                val description = tvShowResult.getString("overview")
                val category = tvShowResult.getJSONArray("genre")
                val poster = posterPath + tvShowResult.getString("poster_path")
                val date = tvShowResult.getString("release_date")

                lateinit var genre :String

                for( j in 0 until category.length()){
                    genre = category[j].toString()
                }

                val tvShow = DataResponse(id.toString(),title,description,genre,poster,date)

                listTvShow.add(tvShow)
            }
        }catch (e : JSONException){
            e.printStackTrace()
        }
        return listTvShow
    }


    fun loadDetailMovie(movieId : String) : DataResponse {
       lateinit var movieResponse : DataResponse
        var posterPath = "https://image.tmdb.org/t/p/w185"

        try{
            val responseObject = JSONObject(parsingFile("responsesMovie.json"))
            val listMovieArray = responseObject.getJSONArray("items")
            for ( i in 0 until listMovieArray.length()){

                val movie = listMovieArray.getJSONObject(i)

                if(movie.getInt("id").toString() == movieId){

                    val id = movie.getInt("id")
                    val title = movie.getString("title")
                    val description = movie.getString("overview")
                    val category = movie.getJSONArray("genre")
                    val poster = posterPath + movie.getString("poster_path")
                    val date = movie.getString("release_date")

                    lateinit var genre : String

                    for(j in 0 until category.length()){
                        genre = category[j].toString()
                    }

                     movieResponse = DataResponse(id.toString(), title,description,genre
                            ,poster,date)
                }

            }
        }catch ( e : JSONException){
            e.printStackTrace()
        }

        return movieResponse as DataResponse
    }



    fun loadDetailTvShow(tvShowId : String) : DataResponse {
        lateinit var tvShowResponse : DataResponse
        var posterPath = "https://image.tmdb.org/t/p/w185"

        try{
            val responseObject = JSONObject(parsingFile("responsesTvshow.json"))
            val listTvShowArray = responseObject.getJSONArray("items")
            for ( i in 0 until listTvShowArray.length()){

                val tvShow = listTvShowArray.getJSONObject(i)

                if(tvShow.getInt("id").toString() == tvShowId){

                    val id = tvShow.getInt("id")
                    val title = tvShow.getString("title")
                    val description = tvShow.getString("overview")
                    val category = tvShow.getJSONArray("genre")
                    val poster = posterPath + tvShow.getString("poster_path")
                    val date = tvShow.getString("release_date")

                    lateinit var genre : String

                    for(j in 0 until category.length()){
                        genre = category[j].toString()
                    }

                    tvShowResponse = DataResponse(id.toString(), title,description,genre
                            ,poster,date)
                }

            }
        }catch ( e : JSONException){
            e.printStackTrace()
        }

        return tvShowResponse as DataResponse
    }



}