package com.example.movies.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.movies.api.OmdbInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieManager() {
    val call = OmdbInstance().movieService()


    private val data: MutableLiveData<List<Movie>> = MutableLiveData()

    fun getMovies(s: String, apikey: String): MutableLiveData<List<Movie>> {
    call.list(s,apikey).clone().enqueue(object : Callback<MovieList> {
        override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
            Log.d("OnResponse","RESPONSE")
            data.postValue(response.body()?.search)
        }

        override fun onFailure(call: Call<MovieList>, t: Throwable) {
            Log.d("OnFail","FAILED")
        }
    })

    return data
    }



}


