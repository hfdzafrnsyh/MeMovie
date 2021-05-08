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
import com.example.memovie.adapter.MovieFavoriteAdapter
import com.example.memovie.databinding.FragmentMovieFavoriteBinding
import com.example.memovie.viewmodel.MovieViewModel
import com.example.memovie.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar


class MovieFavoriteFragment : Fragment() {


    private lateinit var fragmentMovieFavoriteBinding: FragmentMovieFavoriteBinding
    private lateinit var viewModel : MovieViewModel
    private lateinit var movieFavoriteAdapter : MovieFavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        fragmentMovieFavoriteBinding = FragmentMovieFavoriteBinding.inflate(layoutInflater , container , false)
        return fragmentMovieFavoriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouch.attachToRecyclerView(fragmentMovieFavoriteBinding.rvMovieFavorite)



        if( activity != null ){
            val factory = ViewModelFactory.getInstance(requireActivity())
             viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            movieFavoriteAdapter = MovieFavoriteAdapter()


            fragmentMovieFavoriteBinding.progressBar.visibility = View.VISIBLE

            viewModel.getMovieFavorite().observe( viewLifecycleOwner , { movieFavorite ->
                fragmentMovieFavoriteBinding.progressBar.visibility = View.GONE
               movieFavoriteAdapter.submitList(movieFavorite)
            })

            with(fragmentMovieFavoriteBinding.rvMovieFavorite){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieFavoriteAdapter
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
                val movieEntity = movieFavoriteAdapter.getSwipeData(swipePosition)
                movieEntity?.let { viewModel.setMovieFavorite(it) }
                val snackbar = Snackbar.make(view as View, R.string.message_delete, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_berhasil) { v ->
                    movieEntity?.let { viewModel.setMovieFavorite(it) }
                }
                snackbar.show()
            }
        }
    })
}