package com.example.movies.ui.activites

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.ui.adapters.MoviesAdapter
import com.example.movies.ui.fragments.MovieFragment
import com.example.movies.ui.fragments.MoviesSearchedFragment
import com.example.movies.viewmodels.MoviesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_search.*
import kotlinx.android.synthetic.main.dialog_search.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var moviesViewModel: MoviesViewModel
    val moviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter()
    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val movieFragment =  MovieFragment()
                val transaction : FragmentTransaction
                transaction = supportFragmentManager.beginTransaction().add(R.id.fragment_content, movieFragment)
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                moviesViewModel.loadMovies("spiderman","d2e11186")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                moviesViewModel.loadMovies("sky","d2e11186")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        setSupportActionBar(main_toolbar)
        val movieFragment =  MovieFragment()
        val transaction : FragmentTransaction
        transaction = supportFragmentManager.beginTransaction().add(R.id.fragment_content, movieFragment)
        transaction.commit()
        /*
        recycler_view.adapter = moviesAdapter
        recycler_view.layoutManager = GridLayoutManager(this, 2)

        //inicializar o viewmodel
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        moviesViewModel.loadMovies("batman","d2e11186")
        moviesViewModel.getMovies().observe(this, Observer {data ->
            data?.let {
                if (it.isEmpty())
                    Toast.makeText(this,"Lista vazia",Toast.LENGTH_LONG).show()
                else
                moviesAdapter.add(it)
            }
        })
*/
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.action_search) {
            dialogSearchMovie()
        }
            return super.onOptionsItemSelected(item)
    }

    private fun dialogSearchMovie() {
        val layout = LayoutInflater.from(this).inflate(R.layout.dialog_search, null, false)
        val title = layout.input_title
        val dialog = AlertDialog.Builder(this)

        dialog.setView(layout)
        dialog.setNegativeButton("Cancel", null)
        dialog.setPositiveButton("OK") {d, i ->
            val title_recieved = title.text.toString()
            val moviesSearchedFragment =  MoviesSearchedFragment()
            val transaction : FragmentTransaction
            transaction = supportFragmentManager.beginTransaction().replace(R.id.fragment_content, moviesSearchedFragment)
            transaction.commit()
        }
        dialog.create().show()
    }
}
