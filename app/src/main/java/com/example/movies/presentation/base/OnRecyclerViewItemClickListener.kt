package com.example.movies.presentation.base

interface OnRecyclerViewItemClickListener<T> {
    fun onItemClicked(item: T)
}