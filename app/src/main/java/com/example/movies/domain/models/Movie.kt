package com.example.movies.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var title: String,
    val year: Int,
    val cast: List<String>? = null,
    val genres: List<String>? = null,
    val rating: Float
) : Parcelable