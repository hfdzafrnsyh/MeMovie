package com.example.memovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.memovie.adapter.SectionPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionPagerAdapter = SectionPagerAdapter(this , supportFragmentManager)
        viewPager.adapter = sectionPagerAdapter
        tabHome.setupWithViewPager(viewPager)

        supportActionBar?.elevation = 0f

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu , menu)

        return true

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_setting -> {
                val intent = Intent(this ,SetModeActivity::class.java)
                startActivity(intent)
            }

            R.id.menu_favorite -> {
                val intent = Intent(this , MoviesFavoriteActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }


}