package com.example.movies.presentation.movieDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.databinding.ItemPhotoBinding
import com.example.movies.domain.models.Photo

class PhotosAdapter() : PagingDataAdapter<Photo, PhotosAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {
    lateinit var context: Context

    inner class PhotoViewHolder(private val itemPhotoBinding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(itemPhotoBinding.root) {
        fun bind(photo: Photo) {
            Glide.with(context).load(photo.link).fitCenter().placeholder(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_placeholder
                )
            ).into(itemPhotoBinding.photoImageView)
        }
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem.link == newItem.link

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let { photo -> holder.bind(photo) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        context = parent.context
        val binder = ItemPhotoBinding.inflate(LayoutInflater.from(context), parent, false)
        return PhotoViewHolder(binder)
    }
}