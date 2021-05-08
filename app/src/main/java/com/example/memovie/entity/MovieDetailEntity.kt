package com.example.memovie.entity

import androidx.room.Embedded

data class MovieDetailEntity (
        @Embedded
        var mMovie : MovieEntity,

    )