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
import com.jetpack.lumiere.data.source.local.room.FavoriteMovie
import com.jetpack.lumiere.data.source.local.room.FavoriteTvShow
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

    private lateinit var item: FavoriteTvShow
    private lateinit var tvshowId:String
    private lateinit var title:String
    private lateinit var description:String
    private lateinit var year:String
    private lateinit var genre:String
    private lateinit var poster:String

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
            tvshowId = extras.getString(EXTRA_TVSHOW).toString()
            val id = tvshowId.toInt()

            tvShowViewModel.getTvShow(id).observe(this, {
                showLoading(true)
                if (it != null) {
                    url = "${url}${it.title}"

                    title = it.title
                    description = it.description
                    year = it.year
                    genre = it.genre
                    poster = it.poster
                    item = FavoriteTvShow(tvshowId, title, description, year, genre, poster, null)

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

        isFavorited()

        contentDetailTvShowBinding.btnDetailTrailer.setOnClickListener(this)
        contentDetailTvShowBinding.tbFav.setOnClickListener(this)

    }

    private fun isFavorited() {
        tvShowViewModel.isFavorited(application, tvshowId).observe(this, {
            if (it.isNotEmpty()) {
                contentDetailTvShowBinding.tbFav.isChecked = true
            }
        })
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
            R.id.tb_fav -> {
                if (contentDetailTvShowBinding.tbFav.isChecked) {
                    addToFavorite()
                } else {
                    removeFromFavorite()
                }
            }
        }
    }

    private fun removeFromFavorite() {
        tvShowViewModel.delete(application, item)
        Toast.makeText(this, "You just remove $title from favorite", Toast.LENGTH_SHORT).show()
    }

    private fun addToFavorite() {
        tvShowViewModel.insert(application, item)
        Toast.makeText(this, "You just add $title to favorite", Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        activityDetailTvShowBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}