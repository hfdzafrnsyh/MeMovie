package com.example.memovie.source.remote

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.memovie.entity.MovieEntity
import com.example.memovie.entity.TvShowEntity


@Database(entities = [MovieEntity::class , TvShowEntity::class],
        version = 1,
        exportSchema = false
        )
abstract class MeMovieDatabase : RoomDatabase() {
    abstract fun meMovieDao() : MeMovieDao

    companion object {
        @Volatile

        private var INSTANCE : MeMovieDatabase ? = null

        fun getInstance(context : Context ) : MeMovieDatabase =
                INSTANCE ?: synchronized(this){
                    INSTANCE ?: Room.databaseBuilder(context.applicationContext ,
                        MeMovieDatabase::class.java,
                            "MeMovie.db").build()

                }
    }

}