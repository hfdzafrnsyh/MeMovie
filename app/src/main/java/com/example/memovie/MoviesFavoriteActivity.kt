package com.example.memovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.memovie.adapter.FavoritePagerAdapter
import kotlinx.android.synthetic.main.activity_movie_favorite.*

class MoviesFavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_favorite)

        val favoritePagerAdapter = FavoritePagerAdapter(this, supportFragmentManager)
        viewPagerFavorite.adapter = favoritePagerAdapter
        tabFavorite.setupWithViewPager(viewPagerFavorite)

        supportActionBar?.elevation = 0f
        supportActionBar?.title = "Favorite"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}