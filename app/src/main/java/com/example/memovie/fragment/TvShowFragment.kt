package com.example.memovie.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memovie.adapter.TvShowAdapter
import com.example.memovie.databinding.FragmentTvShowBinding
import com.example.memovie.viewmodel.TvShowViewModel
import com.example.memovie.viewmodel.ViewModelFactory
import com.example.memovie.vo.Status

class TvShowFragment : Fragment() {

    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater , container , false)
        return fragmentTvShowBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if( activity != null ) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val tvShowViewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            val tvShowAdapter = TvShowAdapter()

            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE


            tvShowViewModel.getTvShow().observe( viewLifecycleOwner , {tvShow ->
                if(tvShow != null ){
                    when(tvShow.status){
                        Status.LOADING -> fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentTvShowBinding.progressBar.visibility = View.GONE
                            tvShowAdapter.submitList(tvShow.data)
                        }
                        Status.ERROR -> {
                            fragmentTvShowBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context , "Error" , Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentTvShowBinding.rvTvShow){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }

        }
    }

}