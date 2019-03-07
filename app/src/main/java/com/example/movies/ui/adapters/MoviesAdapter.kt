package com.example.movies.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.data.Movie
import com.example.movies.R
import kotlinx.android.synthetic.main.item_movie.view.*
import com.example.movies.ui.DownloadImageWithURLTask


class MoviesViewHolder(val view: View): RecyclerView.ViewHolder(view){

    fun bindView(item: Movie){
    with(view){
        var downloadImage = DownloadImageWithURLTask(iv_movie)
        if (item.poster != "N/A")
        downloadImage.execute(item.poster)
        else
        downloadImage.execute("https://pbs.twimg.com/profile_images/1826025214/atheism2_400x400.png")
        tv_movie.text = item.title
    }

    }

}
class MoviesAdapter(val data: MutableList<Movie> = mutableListOf()): RecyclerView.Adapter<MoviesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int)
            = holder.bindView(data[position])

    fun add(item: List<Movie>){
        data.clear()
        data.addAll(item)
        notifyDataSetChanged()
    }

    fun add(item: Movie){
        data.add(item)
        notifyDataSetChanged()
    }

    fun remove (item: Movie){
        data.remove(item)
        notifyDataSetChanged()
    }

}