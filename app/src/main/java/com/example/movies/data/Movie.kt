package com.example.movies.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Movie
(
    @SerializedName("Title")
    @Expose
    var title: String

)