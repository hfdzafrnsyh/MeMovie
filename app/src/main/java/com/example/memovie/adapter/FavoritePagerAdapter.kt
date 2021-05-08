package com.example.memovie.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.memovie.R
import com.example.memovie.fragment.MovieFavoriteFragment
import com.example.memovie.fragment.TvShowFavoriteFragment

class FavoritePagerAdapter(private val context: Context , fragmentManager : FragmentManager) : FragmentPagerAdapter(fragmentManager , BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int = 2

    companion object {
        @StringRes
        private var TAB_TITLES = intArrayOf(R.string.movie , R.string.tvShow)
    }


    override fun getItem(position: Int): Fragment =
        when(position){
            0 -> MovieFavoriteFragment()
            1 -> TvShowFavoriteFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? = context.resources.getString(TAB_TITLES[position])
}