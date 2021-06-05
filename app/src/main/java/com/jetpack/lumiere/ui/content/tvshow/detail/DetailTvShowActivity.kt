package com.jetpack.lumiere.ui.content.tvshow.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.jetpack.lumiere.R
import com.jetpack.lumiere.data.source.local.entity.TvShowDetailEntity
import com.jetpack.lumiere.data.source.remote.response.TvDetail
import com.jetpack.lumiere.databinding.ActivityDetailTvShowBinding
import com.jetpack.lumiere.databinding.ContentDetailTvShowBinding
import com.jetpack.lumiere.ui.content.tvshow.TvShowsViewModel
import com.jetpack.lumiere.viewmodel.ViewModelFactory

class DetailTvShowActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_TVSHOW = "extra_tvshow"
    }

    private lateinit var contentDetailTvShowBinding: ContentDetailTvShowBinding
    private lateinit var activityDetailTvShowBinding: ActivityDetailTvShowBinding
    private lateinit var tvShowViewModel: TvShowsViewModel

    private var url: String = "https://www.youtube.com/results?search_query="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        contentDetailTvShowBinding = activityDetailTvShowBinding.contentDetailTvshow

        setContentView(activityDetailTvShowBinding.root)

        setSupportActionBar(activityDetailTvShowBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tvShowViewModel = ViewModelProvider(this, ViewModelFactory.getInstance())
            .get(TvShowsViewModel::class.java)

        val extras = intent.extras

        if (extras != null) {
            val tvshowId = extras.getString(EXTRA_TVSHOW).toString()
            val id = tvshowId.toInt()

            tvShowViewModel.getTvShow(id).observe(this, {
                showLoading(true)
                if (it != null) {
                    url = "${url}${it.title}"
                    showDetailTvshow(it)
                    showLoading(false)
                } else {
                    Log.e("TV Show Detail", "TV Show detail empty")
                }
            })
        }

        tvShowViewModel.load.observe(this, {
            activityDetailTvShowBinding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        contentDetailTvShowBinding.btnDetailTrailer.setOnClickListener(this)

    }

    private fun showDetailTvshow(tvshow: TvShowDetailEntity) {
        with(contentDetailTvShowBinding) {
            tvDetailTvshowTitle.text = tvshow.title
            tvDetailTvshowYear.text = tvshow.year
            tvDetailTvshowGenre.text = tvshow.genre
            tvDetailTvshowDescription.text = tvshow.description
            tvDetailTvshowEpisode.text = getString(R.string.value_episode, tvshow.episode)

            com.bumptech.glide.Glide.with(this@DetailTvShowActivity)
                .load("https://image.tmdb.org/t/p/original/${tvshow.poster.trim()}")
                .transform(RoundedCorners(25))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_image)
                .into(imgDetailTvshowPoster)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_detail_trailer -> {
                if (url != "") {
                    val openBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(openBrowser)
                } else {
                    Toast.makeText(this, "There is no trailer that we can show", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        activityDetailTvShowBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}