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
import com.example.memovie.databinding.ItemMovieBinding
import com.example.memovie.entity.MovieEntity

class MovieAdapter : PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK){



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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return MovieViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(movieViewHolder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            movieViewHolder.bind(movie)
        }
    }


    class MovieViewHolder(private val binding : ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

            fun bind(movies : MovieEntity) {
                with(binding){

                    tvTitle.text = movies.title
                    tvDate.text = movies.date

                    Glide.with(itemView.context)
                            .load(movies.poster)
                            .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_broken)
                                    .error(R.drawable.ic_error))
                            .into(imgPoster)

                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context , MovieDetailActivity::class.java)
                        intent.putExtra(MovieDetailActivity.EXTRA_DETAIL , movies.id)
                        itemMovie.context.startActivity(intent)
                    }
                }
            }
    }

}