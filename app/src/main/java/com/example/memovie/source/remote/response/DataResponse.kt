package com.example.memovie.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataResponse(
        var id: String ,
        var title: String ,
        var description: String ,
        var category: String,
        var poster: String ,
        var date: String
) : Parcelable
