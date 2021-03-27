package com.example.movies.presentation.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.ItemMovieBinding
import com.example.movies.domain.models.Movie

class MoviesAdapter() : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    private val moviesList = ArrayList<Movie>()

    class MovieViewHolder(private val binder: ItemMovieBinding) :
        RecyclerView.ViewHolder(binder.root) {
        fun bind(movie: Movie) {
            binder.nameTextView.text = movie.title
            binder.rateValueTextView.text = movie.rating.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    fun submitList(movies:List<Movie>){
        Log . d ("VIEWMODELMOVIES","ADAPTER ${movies.size}")

        differ.submitList(movies)
    }
}

