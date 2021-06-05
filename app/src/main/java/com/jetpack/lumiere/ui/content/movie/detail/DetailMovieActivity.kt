package com.jetpack.lumiere.ui.content.movie.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.jetpack.lumiere.R
import com.jetpack.lumiere.data.source.local.entity.MovieEntity
import com.jetpack.lumiere.data.source.local.room.FavoriteMovie
import com.jetpack.lumiere.databinding.ActivityDetailMovieBinding
import com.jetpack.lumiere.databinding.ContentDetailMovieBinding
import com.jetpack.lumiere.ui.content.movie.MoviesViewModel
import com.jetpack.lumiere.viewmodel.ViewModelFactory

class DetailMovieActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var contentDetailMovieBinding: ContentDetailMovieBinding
    private lateinit var activityDetailMovieBinding: ActivityDetailMovieBinding
    private lateinit var moviesViewModel: MoviesViewModel

    private lateinit var item:FavoriteMovie
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

        moviesViewModel = ViewModelProvider(this, ViewModelFactory.getInstance())
            .get(MoviesViewModel::class.java)

        val extras = intent.extras

        if (extras != null) {
            movieId = extras.getString(EXTRA_MOVIE).toString()
            val id = movieId.toInt()

            showLoading(true)
            moviesViewModel.getMovie(id).observe(this, {
                if (it != null) {
                    url = "${url}${it.title}"
                    title = it.title
                    description = it.description
                    year = it.year
                    genre = it.genre
                    poster = it.poster
                    item = FavoriteMovie(movieId, title, description, year, genre, poster)

                    showDetailMovie(it)
                    showLoading(false)
                } else {
                    Log.e("Movie Detail", "Movie detail empty")
                }
            })

        }

        isFavorited()

        contentDetailMovieBinding.btnDetailTrailer.setOnClickListener(this)
        contentDetailMovieBinding.tbFav.setOnClickListener(this)
    }

    private fun showDetailMovie(movie: MovieEntity) {
        Log.e("tess", movie.title)
        with(contentDetailMovieBinding) {

            tvDetailMovieTitle.text = movie.title
            tvDetailMovieYear.text = movie.year
            tvDetailMovieGenre.text = movie.genre
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
        moviesViewModel.isFavorited(application, movieId).observe(this, {
            if (it.isNotEmpty()) {
                contentDetailMovieBinding.tbFav.isChecked = true
            }
        })
    }


    private fun removeFromFavorite() {
        moviesViewModel.delete(application, item)
        Toast.makeText(this, "You just remove $title from favorite", Toast.LENGTH_SHORT).show()
    }

    private fun addToFavorite() {
        moviesViewModel.insert(application, item)
        Toast.makeText(this, "You just add $title to favorite", Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        activityDetailMovieBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}