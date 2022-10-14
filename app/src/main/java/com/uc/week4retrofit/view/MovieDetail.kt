package com.uc.week4retrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.uc.week4retrofit.adapter.CPAdapter
import com.uc.week4retrofit.adapter.CountryAdapter
import com.uc.week4retrofit.adapter.GenreAdapter
import com.uc.week4retrofit.databinding.ActivityMovieDetailBinding
import com.uc.week4retrofit.helper.Const
import com.uc.week4retrofit.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetail : AppCompatActivity() {

        private lateinit var binding : ActivityMovieDetailBinding
        private lateinit var viewModel: MoviesViewModel
        private lateinit var adapter: GenreAdapter
        private lateinit var imageadapter : CPAdapter
        private lateinit var country : CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movieId = intent.getIntExtra("movie_id", 0)
        Toast.makeText(applicationContext,"Movie_ID, ${movieId}", Toast.LENGTH_SHORT).show()
        binding.LoadingPage.visibility= View.VISIBLE

        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        viewModel.getMovieDetails(Const.API_KEY, movieId)
        viewModel.movieDetails.observe(this, Observer{
            response->

            //genre
            binding.horizontalRv.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            adapter = GenreAdapter(response.genres)
            binding.horizontalRv.adapter = adapter

            //country
            binding.CountryRv.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            country = CountryAdapter(response.production_countries)
            binding.CountryRv.adapter = country

            //image
            binding.LogoRv.layoutManager=LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
            imageadapter = CPAdapter(response.production_companies)
            binding.LogoRv.adapter = imageadapter

            //loading
            binding.LoadingPage.visibility= View.INVISIBLE

            //title
            binding.tvMovieDetail.apply {
                text = response.title
            }

            //decription
            binding.tvDeskripsi.apply {
                text = response.overview
            }

            //image Film
            Glide.with(applicationContext).load(Const.IMG_URL + response.backdrop_path).into(binding.imageViewMovieDetail)
        })

    }
}