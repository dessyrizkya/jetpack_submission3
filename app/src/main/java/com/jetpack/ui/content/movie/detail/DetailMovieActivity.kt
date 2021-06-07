package com.jetpack.ui.content.movie.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.jetpack.R
import com.jetpack.data.source.local.entity.MovieEntity
import com.jetpack.data.source.local.room.FavoriteMovie
import com.jetpack.data.source.remote.response.GenresItem
import com.jetpack.data.source.remote.response.ResultsItem
import com.jetpack.databinding.ActivityDetailMovieBinding
import com.jetpack.databinding.ContentDetailMovieBinding
import com.jetpack.ui.content.movie.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var contentDetailMovieBinding: ContentDetailMovieBinding
    private lateinit var activityDetailMovieBinding: ActivityDetailMovieBinding
    private val movieViewModel : MovieViewModel by viewModels()

    private lateinit var item: FavoriteMovie
    private lateinit var movieId:String
    private lateinit var title:String
    private lateinit var description:String
    private lateinit var year:String
    private lateinit var genre:String
    private lateinit var poster:String

    private var url: String = "https://www.youtube.com/results?search_query="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        contentDetailMovieBinding = activityDetailMovieBinding.contentDetailMovie

        setContentView(activityDetailMovieBinding.root)

        setSupportActionBar(activityDetailMovieBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.getParcelableExtra<MovieEntity>(EXTRA_MOVIE)

        if (extras != null) {
            movieId = extras.movieId
            title = extras.title
            description = extras.description
            year = extras.year
            genre = extras.genre
            poster = extras.poster

            url = "${url}${title}"

            item = FavoriteMovie(movieId, title, description, year, genre, poster)

            showLoading(true)
            showDetailMovie(extras)
            showLoading(false)
        }

        isFavorited()

        contentDetailMovieBinding.btnDetailTrailer.setOnClickListener(this)
        contentDetailMovieBinding.tbFav.setOnClickListener(this)
    }

    private fun showDetailMovie(movie: MovieEntity) {
        with(contentDetailMovieBinding) {

            tvDetailMovieGenre.text = movie.genre
            tvDetailMovieTitle.text = movie.title
            tvDetailMovieYear.text = movie.year

            tvDetailMovieDescription.text = movie.description

            Glide.with(this@DetailMovieActivity)
                .load("https://image.tmdb.org/t/p/original/${movie.poster.trim()}")
                .transform(RoundedCorners(25))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_image)
                .into(imgDetailMoviePoster)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_detail_trailer -> {
                if (url != "-") {
                    val openBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(openBrowser)
                } else {
                    Toast.makeText(this, "There is no trailer that we can show", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.tb_fav -> {
                if (contentDetailMovieBinding.tbFav.isChecked) {
                   addToFavorite()
                } else {
                   removeFromFavorite()
                }
            }
        }
    }

    private fun isFavorited(){
        movieViewModel.isFavorited(movieId).observe(this, {
            if (it.isNotEmpty()) {
                contentDetailMovieBinding.tbFav.isChecked = true
            }
        })
    }


    private fun removeFromFavorite() {
        movieViewModel.delete(item)
        Toast.makeText(this, "You just remove $title from favorite", Toast.LENGTH_SHORT).show()
    }

    private fun addToFavorite() {
        movieViewModel.insert(item)
        Toast.makeText(this, "You just add $title to favorite", Toast.LENGTH_SHORT).show()
    }


    private fun showLoading(isLoading: Boolean) {
        activityDetailMovieBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}