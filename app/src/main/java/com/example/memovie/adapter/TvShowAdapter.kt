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
import com.example.memovie.databinding.ItemTvshowBinding
import com.example.memovie.entity.TvShowEntity

class TvShowAdapter : PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {



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



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemTvShowBinding = ItemTvshowBinding.inflate(LayoutInflater.from(parent.context), parent , false )
        return TvShowViewHolder(itemTvShowBinding)
    }

    override fun onBindViewHolder(tvShowViewHolder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            tvShowViewHolder.bind(tvShow)
        }
    }



    class TvShowViewHolder(private val binding : ItemTvshowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow : TvShowEntity){
            with(binding){
                tvTitle.text = tvshow.title
                tvDate.text = tvshow.date

                Glide.with(itemView.context)
                        .load(tvshow.poster)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_broken)
                                .error(R.drawable.ic_error))
                        .into(imgPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context , TvshowDetailActivity::class.java)
                    intent.putExtra(TvshowDetailActivity.EXTRA_DETAIL , tvshow.id)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}