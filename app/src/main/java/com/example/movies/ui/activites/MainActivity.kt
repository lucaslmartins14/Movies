package com.example.movies.ui.activites

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.ui.adapters.MoviesAdapter
import com.example.movies.viewmodels.MoviesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var moviesViewModel: MoviesViewModel
    val moviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter()
    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                moviesViewModel.loadMovies("batman","d2e11186")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                moviesViewModel.loadMovies("spiderman","d2e11186")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                moviesViewModel.loadMovies("aquaman","d2e11186")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        recycler_view.adapter = moviesAdapter
        recycler_view.layoutManager = GridLayoutManager(this, 2)

        //inicializar o viewmodel
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        moviesViewModel.getMovies().observe(this, Observer {data ->
            data?.let {
                if (it.isEmpty())
                    Toast.makeText(this,"Lista vazia",Toast.LENGTH_LONG).show()
                else
                moviesAdapter.add(it)
            }
        })

        moviesViewModel.loadMovies("batman","d2e11186")
    }
}
