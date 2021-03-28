package com.example.movies.presentation.searchMovies

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.ItemYearMoviesBinding
import com.example.movies.domain.models.Movie
import com.example.movies.presentation.base.OnRecyclerViewItemClickListener
import com.example.movies.presentation.home.MoviesAdapter
import com.example.movies.presentation.models.YearMovies
import com.example.movies.presentation.movieDetails.DETAILS_INTENT_KEY
import com.example.movies.presentation.movieDetails.MovieDetailsActivity

class SearchResultsAdapter : RecyclerView.Adapter<SearchResultsAdapter.SearchResultViewHolder>() {
    private var context: Context? = null
    val yearsMovies = ArrayList<YearMovies>()

    inner class SearchResultViewHolder(private val itemYearMoviesBinding: ItemYearMoviesBinding) :
        RecyclerView.ViewHolder(itemYearMoviesBinding.root) {
        fun bind(yearMovies: YearMovies) {
            itemYearMoviesBinding.yearTextView.text = yearMovies.year.toString()
            val adapter = MoviesAdapter()
            val linearLayoutManager = LinearLayoutManager(context)
            itemYearMoviesBinding.yearMoviesRecyclerView.layoutManager = linearLayoutManager
            itemYearMoviesBinding.yearMoviesRecyclerView.adapter=adapter
            adapter.submitList(yearMovies.movies)
            adapter.onRecyclerViewItemClickListener=object :OnRecyclerViewItemClickListener<Movie>{
                override fun onItemClicked(item: Movie) {
                    val intent = Intent(context,MovieDetailsActivity::class.java)
                    intent.putExtra(DETAILS_INTENT_KEY,item)
                    context?.startActivity(intent)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        context = parent.context
        val viewBinder = ItemYearMoviesBinding.inflate(LayoutInflater.from(context), parent, false)
        return SearchResultViewHolder(viewBinder)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(differ.currentList[position])

    }

    override fun getItemCount(): Int = differ.currentList.size


    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<YearMovies>() {
        override fun areItemsTheSame(oldItem: YearMovies, newItem: YearMovies): Boolean {
            return oldItem.year == newItem.year
        }

        override fun areContentsTheSame(oldItem: YearMovies, newItem: YearMovies): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)
    fun submitList(list:List<YearMovies>)=differ.submitList(list)
    fun clearList(){

        differ.submitList(ArrayList())
    }
}