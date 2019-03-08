package com.example.movies.ui.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager

import com.example.movies.R
import com.example.movies.ui.adapters.MoviesAdapter
import com.example.movies.viewmodels.MoviesViewModel
import kotlinx.android.synthetic.main.movie_fragment.*

class MovieFragment : Fragment() {

    companion object {
        fun newInstance() = MovieFragment()
    }

    private lateinit var moviesViewModel: MoviesViewModel
    val moviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler_view.adapter = moviesAdapter
        recycler_view.layoutManager = GridLayoutManager(activity, 2)
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        moviesViewModel.loadMovies("batman","d2e11186")
        moviesViewModel.getMovies().observe(this, Observer {data ->
            data?.let {
                if (it.isEmpty())
                    Toast.makeText(activity,"Lista vazia", Toast.LENGTH_LONG).show()
                else
                    moviesAdapter.add(it)
            }
        })
    }

}