package com.example.movies.ui.activites

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.movies.R
import com.example.movies.ui.adapters.MoviesAdapter
import com.example.movies.ui.fragments.*
import com.example.movies.viewmodels.MoviesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_search.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var moviesViewModel: MoviesViewModel
    val moviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter()
    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_movies -> {
                val movieFragment =  MovieFragment()
                val transaction : FragmentTransaction
                transaction = supportFragmentManager.beginTransaction().replace(R.id.fragment_content, movieFragment)
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_series -> {
                val seriesFragment =  SeriesFragment()
                val transaction : FragmentTransaction
                transaction = supportFragmentManager.beginTransaction().replace(R.id.fragment_content, seriesFragment)
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_games -> {
                val gameFragment =  GameFragment()
                val transaction : FragmentTransaction
                transaction = supportFragmentManager.beginTransaction().replace(R.id.fragment_content, gameFragment)
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        //setSupportActionBar(main_toolbar)
        val movieFragment =  MovieFragment()
        val transaction : FragmentTransaction
        transaction = supportFragmentManager.beginTransaction().replace(R.id.fragment_content, movieFragment)
        transaction.commit()
    }

    private fun dialogSearchMovie() {
        val layout = LayoutInflater.from(this).inflate(R.layout.dialog_search, null, false)
        val title = layout.input_title
        val dialog = AlertDialog.Builder(this)
        dialog.setView(layout)
        dialog.setNegativeButton("Cancel", null)
        dialog.setPositiveButton("OK") {d, i ->
            val title_recieved = title.text.toString()
            val bundle =  Bundle()
            bundle.putString("title_recived", title_recieved)
            val moviesSearchedFragment =  MoviesSearchedFragment()
            moviesSearchedFragment.arguments = bundle
            val transaction : FragmentTransaction
            transaction = supportFragmentManager.beginTransaction().replace(R.id.fragment_content, moviesSearchedFragment)
            transaction.commit()
        }
        dialog.create().show()
    }

}
