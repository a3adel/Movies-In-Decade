package com.example.movies.presentation.searchMovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.ItemYearMovieBinding
import com.example.movies.presentation.models.YearMovie

class YearMoviesAdapter : RecyclerView.Adapter<YearMoviesAdapter.YearMovieViewHolder>() {
    class YearMovieViewHolder(private val itemYearMovieBinding: ItemYearMovieBinding) :
        RecyclerView.ViewHolder(itemYearMovieBinding.root) {
        fun bind(yearMovie: YearMovie) {
            itemYearMovieBinding.nameTextView.text = yearMovie.title
            itemYearMovieBinding.rateValueTextView.text = yearMovie.rating.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YearMovieViewHolder {
        val binder =
            ItemYearMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return YearMovieViewHolder(binder)
    }

    override fun onBindViewHolder(holder: YearMovieViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size


    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<YearMovie>() {
        override fun areItemsTheSame(oldItem: YearMovie, newItem: YearMovie): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: YearMovie, newItem: YearMovie): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)
    fun submitList(list:List<YearMovie>) = differ.submitList(list)
}