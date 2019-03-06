package com.example.movies.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.data.Movie
import com.example.movies.data.MovieManager

class MoviesViewModel: ViewModel() {
    private val movieManager = MovieManager()

    private val mMovies = MutableLiveData<List<Movie>>()

    fun getMovies(): MutableLiveData<List<Movie>> = mMovies

    fun loadMovies(s: String, apikey: String){
        val tmp = movieManager.getMovies(s,apikey)
        mMovies.postValue(tmp)
    }
}