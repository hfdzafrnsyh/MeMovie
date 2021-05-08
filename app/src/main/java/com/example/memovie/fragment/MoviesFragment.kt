package com.example.memovie.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memovie.adapter.MovieAdapter
import com.example.memovie.databinding.FragmentMoviesBinding
import com.example.memovie.viewmodel.MovieViewModel
import com.example.memovie.viewmodel.ViewModelFactory
import com.example.memovie.vo.Status

class MoviesFragment : Fragment() {

    private lateinit var fragmentMovieBinding : FragmentMoviesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

      fragmentMovieBinding = FragmentMoviesBinding.inflate(layoutInflater , container , false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if( activity != null ) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            fragmentMovieBinding.progressBar.visibility = View.VISIBLE

            val movieAdapter = MovieAdapter()

            movieViewModel.getMovie().observe( viewLifecycleOwner , {movie ->
                if(movie != null ){
                    when(movie.status){
                        Status.LOADING -> fragmentMovieBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentMovieBinding.progressBar.visibility = View.GONE
                            movieAdapter.submitList(movie.data)
                        }
                        Status.ERROR -> {
                            fragmentMovieBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context , "Error" , Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentMovieBinding.rvMovie){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

        }

    }

}