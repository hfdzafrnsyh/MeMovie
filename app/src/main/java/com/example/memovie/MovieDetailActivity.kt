package com.example.memovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.memovie.databinding.ActivityDetailBinding
import com.example.memovie.databinding.ContentDetailBinding
import com.example.memovie.entity.MovieEntity
import com.example.memovie.viewmodel.DetailViewModel
import com.example.memovie.viewmodel.ViewModelFactory
import com.example.memovie.vo.Status

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_DETAIL = "extra_detail"
    }

    private lateinit var menu: Menu
    private lateinit var detailBinding : ContentDetailBinding
    private lateinit var viewModel : DetailViewModel
    private lateinit var activityDetailBinding: ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailBinding =  activityDetailBinding.detailContent
         setContentView(activityDetailBinding.root)

        supportActionBar?.title="Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this , factory)[DetailViewModel::class.java]

        val extras = intent.extras

        if( extras != null ) {
            val data = extras.getString(EXTRA_DETAIL)

            if( data != null ) {

                viewModel.setMovie(data)

                viewModel.getDataMovie.observe(this , {detailMovie ->
                    if(detailMovie != null ){
                        when(detailMovie.status){


                            Status.LOADING -> activityDetailBinding.progressBar.visibility = View.VISIBLE

                            Status.SUCCESS -> if(detailMovie.data != null ){
                                activityDetailBinding.progressBar.visibility= View.GONE
                                activityDetailBinding.content.visibility = View.VISIBLE
                                popDetailMovie(detailMovie.data.mMovie)

                            }

                            Status.ERROR -> {
                                activityDetailBinding.progressBar.visibility = View.GONE
                                Toast.makeText(this,"Terjadi kesalahan" , Toast.LENGTH_SHORT).show()
                            }
                        }

                    }
                })

            }

        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_detail , menu)
        this.menu = menu

        viewModel.getDataMovie.observe(this , { movieFavorite ->

            if (movieFavorite != null ){
                when(movieFavorite.status){

                    Status.LOADING -> activityDetailBinding.progressBar.visibility = View.VISIBLE

                    Status.SUCCESS ->  if(movieFavorite.data != null){
                        activityDetailBinding.progressBar.visibility = View.GONE
                        val state = movieFavorite.data.mMovie.favorite
                                setStateFavorite(state)
                    }

                    Status.ERROR -> {
                        activityDetailBinding.progressBar.visibility = View.GONE
                        Toast.makeText(applicationContext , "Terjadi Kesalahan" , Toast.LENGTH_SHORT).show()
                    }


                }
            }
        })



        return true
    }

    private fun popDetailMovie(movieEntity: MovieEntity) {
        detailBinding.tvTitle.text = movieEntity.title
        detailBinding.tvDate.text = movieEntity.date
        detailBinding.tvCategory.text = movieEntity.category
        detailBinding.tvDescription.text = movieEntity.description

        Glide.with(this)
                .load(movieEntity.poster)
                .transform(RoundedCorners(10))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_broken)
                        .error(R.drawable.ic_error))
                .into(detailBinding.imgPoster)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_favorite){
            viewModel.setMovieFavorite()
            return true
        }

        return super.onOptionsItemSelected(item)

    }

    private fun setStateFavorite(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
            Toast.makeText(this, "Favorite" , Toast.LENGTH_SHORT).show()
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)
            Toast.makeText(this, "No Favorite" , Toast.LENGTH_SHORT).show()
        }
    }



}