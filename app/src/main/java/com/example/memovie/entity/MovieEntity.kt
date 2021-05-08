package com.example.memovie.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movieentities")
data class MovieEntity(

        @PrimaryKey
        @NonNull

        @ColumnInfo(name="id")
        var id: String,

        @ColumnInfo(name="title")
        var title: String ,

        @ColumnInfo(name="description")
        var description: String ,

        @ColumnInfo(name="category")
        var category: String ,

        @ColumnInfo(name="poster")
        var poster: String  ,

        @ColumnInfo(name="favorite")
        var favorite : Boolean = false,

        @ColumnInfo(name="date")
        var date: String
        )
