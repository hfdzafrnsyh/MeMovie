package com.example.memovie.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memovie.R
import com.example.memovie.adapter.TvShowFavoriteAdapter
import com.example.memovie.databinding.FragmentTvShowFavoriteBinding
import com.example.memovie.viewmodel.TvShowViewModel
import com.example.memovie.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar


class TvShowFavoriteFragment : Fragment() {

    private lateinit var fragmentTvShowFavoriteBinding: FragmentTvShowFavoriteBinding
    private lateinit var viewModel: TvShowViewModel
    private lateinit var tvShowFavoriteAdapter: TvShowFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentTvShowFavoriteBinding = FragmentTvShowFavoriteBinding.inflate(layoutInflater , container , false)
        return fragmentTvShowFavoriteBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouch.attachToRecyclerView(fragmentTvShowFavoriteBinding.rvTvShowFavorite)


        if( activity != null ){
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            tvShowFavoriteAdapter = TvShowFavoriteAdapter()

           fragmentTvShowFavoriteBinding.progressBar.visibility = View.VISIBLE

           viewModel.getTvShowFavorite().observe( viewLifecycleOwner , { tvShowFavorite ->
                fragmentTvShowFavoriteBinding.progressBar.visibility = View.GONE
                tvShowFavoriteAdapter.submitList(tvShowFavorite)
            })

            with(fragmentTvShowFavoriteBinding.rvTvShowFavorite){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowFavoriteAdapter
            }

        }

    }


    private val itemTouch = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
                makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipePosition = viewHolder.adapterPosition
                val tvShowEntity = tvShowFavoriteAdapter.getSwipeData(swipePosition)
                tvShowEntity?.let { viewModel.setTvShowFavorite(it) }
                val snackbar = Snackbar.make(view as View, R.string.message_delete, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_berhasil) { v ->
                    tvShowEntity?.let { viewModel.setTvShowFavorite(it) }
                }
                snackbar.show()
            }
        }
    })

}