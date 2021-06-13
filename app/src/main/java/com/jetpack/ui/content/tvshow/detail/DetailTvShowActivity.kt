package com.jetpack.ui.content.tvshow.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.jetpack.R
import com.jetpack.data.local.entity.MovieEntity
import com.jetpack.data.local.entity.TvShowEntity
import com.jetpack.databinding.ActivityDetailMovieBinding
import com.jetpack.databinding.ActivityDetailTvShowBinding
import com.jetpack.databinding.ContentDetailMovieBinding
import com.jetpack.databinding.ContentDetailTvShowBinding
import com.jetpack.ui.content.movie.MovieViewModel
import com.jetpack.ui.content.movie.detail.DetailMovieActivity
import com.jetpack.ui.content.tvshow.TvShowViewModel
import com.jetpack.vo.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTvShowActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailTvShowBinding
    private lateinit var contentBinding: ContentDetailTvShowBinding
    private val viewModel : TvShowViewModel by viewModels()
    private var url: String = "https://www.youtube.com/results?search_query="

    companion object {
        const val EXTRA_TVSHOW = " extra_tvshow"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contentBinding = binding.contentDetailTvshow

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getStringExtra(EXTRA_TVSHOW)
        observeDetailTvShow(id.toString())
    }

    private fun observeDetailTvShow(id: String) {
        viewModel.getDetail(id.toInt()).observe(this, { tv ->
            if (tv != null) {
                when(tv.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        showLoading(false)
                        val item = TvShowEntity(
                            tv.data?.tvshowId.toString(),
                            tv.data?.title.toString(),
                            tv.data?.description.toString(),
                            tv.data?.year.toString(),
                            tv.data?.genre.toString(),
                            tv.data?.poster.toString(),
                            tv.data?.episode.toString(),
                            tv.data!!.isFavorited,
                        )
                        showDetailTvShow(item)
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

    private fun showDetailTvShow(tvshow: TvShowEntity) {
        isFavorited(tvshow.isFavorited)
        with(contentBinding) {

            tvDetailTvshowTitle.text = tvshow.title
            tvDetailTvshowYear.text = tvshow.year
            tvDetailTvshowGenre.text = tvshow.genre
            tvDetailTvshowDescription.text = tvshow.description
            tvDetailTvshowEpisode.text = getString(R.string.value_episode, tvshow.episode)


            Glide.with(this@DetailTvShowActivity)
                .load("https://image.tmdb.org/t/p/original/${tvshow.poster.trim()}")
                .transform(RoundedCorners(25))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_image)
                .into(imgDetailTvshowPoster)

            url = "${url}${tvshow.title}"
            btnDetailTrailer.setOnClickListener(this@DetailTvShowActivity)
            tbFav.setOnClickListener { setFavorite(tvshow) }
        }
    }

    private fun setFavorite(tvshow: TvShowEntity) {
        tvshow.isFavorited = !tvshow.isFavorited
        viewModel.setFav(tvshow)

        if (contentBinding.tbFav.isChecked) {
            Toast.makeText(this, "You just add ${tvshow.title} to favorite", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "You just remove ${tvshow.title} from favorite", Toast.LENGTH_SHORT).show()
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