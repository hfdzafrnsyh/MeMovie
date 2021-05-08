package com.example.memovie.entity

import androidx.room.Embedded

data class TvShowDetailEntity(
        @Embedded
        var mTvShow : TvShowEntity
)
