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
import com.example.memovie.databinding.ActivityTvShowBinding
import com.example.memovie.databinding.ContentDetailBinding
import com.example.memovie.entity.TvShowEntity
import com.example.memovie.viewmodel.DetailViewModel
import com.example.memovie.viewmodel.ViewModelFactory
import com.example.memovie.vo.Status

class TvshowDetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_DETAIL = "extra_detail"
    }

    private lateinit var menu: Menu
    private lateinit var detailBinding: ContentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var tvshowActivityDetailBinding : ActivityTvShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         tvshowActivityDetailBinding = ActivityTvShowBinding.inflate(layoutInflater)
        detailBinding = tvshowActivityDetailBinding.detailContent
        setContentView(tvshowActivityDetailBinding.root)

        supportActionBar!!.title = "Detail"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]


        val extras = intent.extras

        if (extras != null) {
            val data = extras.getString(EXTRA_DETAIL)

            if (data != null) {

                viewModel.setTvShow(data)

                viewModel.getDataTvShow.observe(this, { detailTvShow ->
                    if (detailTvShow != null) {
                        when (detailTvShow.status) {


                            Status.LOADING -> tvshowActivityDetailBinding.progressBar.visibility = View.VISIBLE

                            Status.SUCCESS -> if (detailTvShow.data != null) {
                                tvshowActivityDetailBinding.progressBar.visibility = View.GONE
                                tvshowActivityDetailBinding.content.visibility = View.VISIBLE
                                popDetailTvShow(detailTvShow.data.mTvShow)
                            }

                            Status.ERROR -> {
                                tvshowActivityDetailBinding.progressBar.visibility = View.GONE
                                Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }


                        }
                    }
                })

            }

        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu

        viewModel.getDataTvShow.observe(this , { tvShowFavorite ->

            if(tvShowFavorite != null ){
                when(tvShowFavorite.status ){
                    Status.LOADING -> tvshowActivityDetailBinding.progressBar.visibility = View.VISIBLE

                    Status.SUCCESS -> if(tvShowFavorite.data != null){
                        tvshowActivityDetailBinding.progressBar.visibility = View.GONE
                        val state = tvShowFavorite.data.mTvShow.favorite
                        setStateFavorite(state)
                    }

                    Status.ERROR -> {
                        tvshowActivityDetailBinding.progressBar.visibility = View.GONE
                        Toast.makeText(applicationContext , "Terjadi Kesalahan" , Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })


        return true
    }


    private fun popDetailTvShow(tvShowEntity: TvShowEntity) {
        detailBinding.tvTitle.text = tvShowEntity.title
        detailBinding.tvDate.text = tvShowEntity.date
        detailBinding.tvCategory.text = tvShowEntity.category
        detailBinding.tvDescription.text = tvShowEntity.description

        Glide.with(this)
                .load(tvShowEntity.poster)
                .transform(RoundedCorners(10))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_refresh)
                        .error(R.drawable.ic_error))
                .into(detailBinding.imgPoster)


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            viewModel.setTvShowFavorite()
            return true
        }

        return super.onOptionsItemSelected(item)

    }


    private fun setStateFavorite(state : Boolean ) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
            Toast.makeText(this, "Favorite", Toast.LENGTH_SHORT).show()
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)
            Toast.makeText(this, "No Favorite", Toast.LENGTH_SHORT).show()
        }

    }




}
