package com.example.memovie.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.memovie.R
import com.example.memovie.TvshowDetailActivity
import com.example.memovie.databinding.ItemFavoriteBinding
import com.example.memovie.entity.TvShowEntity

class TvShowFavoriteAdapter() : PagedListAdapter<TvShowEntity, TvShowFavoriteAdapter.TvShowFavoriteViewHolder>(DIFF_CALLBACK) {



    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowFavoriteViewHolder {
       val itemTvShowFavoriteBinding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return TvShowFavoriteViewHolder(itemTvShowFavoriteBinding)
    }

    override fun onBindViewHolder(tvShowFavoriteViewHolder: TvShowFavoriteViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            tvShowFavoriteViewHolder.bind(tvShow)
        }
    }


    fun getSwipeData(swipedPosition : Int) : TvShowEntity ? = getItem(swipedPosition)


    class TvShowFavoriteViewHolder(private val binding : ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(favorite : TvShowEntity){
            with(binding){
                tvTitle.text = favorite.title
                tvDate.text = favorite.date

                Glide.with(itemView.context)
                        .load(favorite.poster)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_broken)
                                .error(R.drawable.ic_error))
                        .into(imgPoster)


                itemView.setOnClickListener {
                    val intent = Intent(itemView.context , TvshowDetailActivity::class.java)
                    intent.putExtra(TvshowDetailActivity.EXTRA_DETAIL , favorite.id)
                    itemView.context.startActivity(intent)
                }

            }
        }
    }
}