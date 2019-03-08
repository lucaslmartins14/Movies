package com.example.movies.ui.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import com.example.movies.R
import com.example.movies.ui.DownloadImageWithURLTask
import com.example.movies.viewmodels.MoviesViewModel
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.movie_details_fragment.*

class MovieDetails : Fragment() {

    companion object {
        fun newInstance() = MovieDetails()
    }


    private lateinit var moviesViewModel: MoviesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.movie_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
        }
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setTitle("")

        val id_recived = arguments!!.getString("imdbid_recived")
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        moviesViewModel.loadMovieDetail(id_recived, "d2e11186")
        moviesViewModel.getMovieDetail().observe(this, Observer {

            tv_title_detail.setText(it.title)
            tv_movie_plot_detail.setText(it.plot)
            tv_type_detail.setText(it.type)
            tv_year_detail.setText(it.year)
            var downloadImage = DownloadImageWithURLTask(iv_movie_detail)
            if (it.poster != "N/A")
                downloadImage.execute(it.poster)
            else
                downloadImage.execute("https://pbs.twimg.com/profile_images/1826025214/atheism2_400x400.png")

        })

    }




}
