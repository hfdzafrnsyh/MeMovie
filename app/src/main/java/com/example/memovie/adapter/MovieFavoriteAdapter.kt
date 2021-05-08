package com.example.memovie.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.memovie.MovieDetailActivity
import com.example.memovie.R
import com.example.memovie.databinding.ItemFavoriteBinding
import com.example.memovie.entity.MovieEntity


class MovieFavoriteAdapter : PagedListAdapter<MovieEntity, MovieFavoriteAdapter.MovieFavoriteViewHolder>(DIFF_CALLBACK) {



    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFavoriteViewHolder {
        val itemMovieFavoriteBinding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context) , parent, false)
        return MovieFavoriteViewHolder(itemMovieFavoriteBinding)
    }

    override fun onBindViewHolder(movieFavoriteViewHolder: MovieFavoriteViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            movieFavoriteViewHolder.bind(movie)
        }
    }


    fun getSwipeData(swipedPosition : Int) : MovieEntity ? = getItem(swipedPosition)


    class MovieFavoriteViewHolder(private val binding : ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(favorite : MovieEntity){
            with(binding){
                tvTitle.text = favorite.title
                tvDate.text = favorite.date


                Glide.with(itemView.context)
                        .load(favorite.poster)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_broken)
                                .error(R.drawable.ic_error))
                        .into(imgPoster)


                itemView.setOnClickListener {
                    val intent = Intent(itemView.context , MovieDetailActivity::class.java)
                    intent.putExtra(MovieDetailActivity.EXTRA_DETAIL , favorite.id)
                    itemFavorite.context.startActivity(intent)
                }
            }
        }
    }

}