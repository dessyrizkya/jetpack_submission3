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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.jetpack.R
import com.jetpack.data.local.entity.MovieEntity
import com.jetpack.databinding.ActivityDetailMovieBinding
import com.jetpack.databinding.ContentDetailMovieBinding
import com.jetpack.databinding.FragmentMovieBinding
import com.jetpack.ui.content.movie.MovieViewModel
import com.jetpack.vo.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var contentBinding: ContentDetailMovieBinding
    private val viewModel : MovieViewModel by viewModels()
    private var url: String = "https://www.youtube.com/results?search_query="

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contentBinding = binding.contentDetailMovie

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getStringExtra(EXTRA_MOVIE)
        observeDetailMovie(id.toString())
    }

    private fun observeDetailMovie(id: String) {
        viewModel.getDetail(id.toInt()).observe(this, { movie->
            if (movie != null) {
                when(movie.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        showLoading(false)
                        val item = MovieEntity(
                            movie.data?.movieId.toString(),
                            movie.data?.title.toString(),
                            movie.data?.description.toString(),
                            movie.data?.year.toString(),
                            movie.data?.genre.toString(),
                            movie.data?.poster.toString(),
                            movie.data!!.isFavorited,
                        )
                        showDetailMovie(item)
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun isFavorited(isFav : Boolean) {
        contentBinding.tbFav.isChecked = isFav
    }

    private fun showDetailMovie(movie: MovieEntity) {
        isFavorited(movie.isFavorited)

        with(contentBinding) {
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

            url = "${url}${movie.title}"
            btnDetailTrailer.setOnClickListener(this@DetailMovieActivity)
            tbFav.setOnClickListener { setFavorite(movie) }
        }
    }

    private fun setFavorite(movie: MovieEntity) {
        viewModel.setFav(movie)

        if (contentBinding.tbFav.isChecked) {
            Toast.makeText(this, "You just add ${movie.title} to favorite", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "You just remove ${movie.title} from favorite", Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(this, resources.getString(R.string.trailer_message), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}