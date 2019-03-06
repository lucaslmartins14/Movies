package com.example.movies.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.movies.api.Interfaces.OmdbInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieManager() {
    val call = OmdbInstance().movieService()


    private var data: List<Movie>? = null

    fun getMovies(s: String, apikey: String): List<Movie>? {
    call.list(s,apikey).clone().enqueue(object : Callback<MovieList> {
        override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
            Log.d("OnResponse","RESPONSE")
            data = response.body()?.search
        }

        override fun onFailure(call: Call<MovieList>, t: Throwable) {
            Log.d("OnFail","FAILED")
        }
    })

    return data
    }



}


