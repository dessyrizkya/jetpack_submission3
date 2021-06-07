package com.jetpack.ui.content.tvshow.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.jetpack.R
import com.jetpack.data.source.local.entity.MovieEntity
import com.jetpack.data.source.local.entity.TvShowDetailEntity
import com.jetpack.data.source.local.room.FavoriteMovie
import com.jetpack.data.source.local.room.FavoriteTvShow
import com.jetpack.databinding.ActivityDetailTvShowBinding
import com.jetpack.databinding.ContentDetailTvShowBinding
import com.jetpack.ui.content.movie.detail.DetailMovieActivity
import com.jetpack.ui.content.tvshow.TvshowViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTvShowActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_TVSHOW = " extra_tvshow"
    }

    private lateinit var contentDetailTvShowBinding: ContentDetailTvShowBinding
    private lateinit var activityDetailTvShowBinding: ActivityDetailTvShowBinding
    private val tvShowViewModel: TvshowViewModel by viewModels()

    private lateinit var item: FavoriteTvShow
    private lateinit var tvshowId:String
    private var title:String = ""
    private var description:String = ""
    private var year:String = ""
    private var genre:String = ""
    private var poster:String = ""
    private var episode:String = ""

    private var url: String = "https://www.youtube.com/results?search_query="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        contentDetailTvShowBinding = activityDetailTvShowBinding.contentDetailTvshow

        setContentView(activityDetailTvShowBinding.root)

        setSupportActionBar(activityDetailTvShowBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.getParcelableExtra<TvShowDetailEntity>(EXTRA_TVSHOW)


        if (extras != null) {
            tvshowId = extras.tvshowId

            if (extras.episode == "") {
                tvShowViewModel.getTvShow(tvshowId.toInt()).observe(this, {
                    if (it != null) {
                        title = it.title
                        description = it.description
                        year = it.year
                        genre = it.genre
                        poster = it.poster
                        episode = it.episode

                        url = "${url}${it.title}"

                        showDetailTvshow(it)
                    } else {
                        Log.e("TV Show Detail", "TV Show detail empty")
                    }
                })
            } else {
                title = extras.title
                description = extras.description
                year = extras.year
                genre = extras.genre
                poster = extras.poster
                episode = extras.episode

                url = "${url}${extras.title}"

                showDetailTvshow(extras)
            }
        }

        isFavorited()

        contentDetailTvShowBinding.btnDetailTrailer.setOnClickListener(this)
        contentDetailTvShowBinding.tbFav.setOnClickListener(this)
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
                if (url != "-") {
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

    private fun isFavorited(){
        tvShowViewModel.isFavorited(tvshowId).observe(this, {
            if (it.isNotEmpty()) {
                contentDetailTvShowBinding.tbFav.isChecked = true
            }
        })
    }


    private fun removeFromFavorite() {
        item = FavoriteTvShow(tvshowId, title, description, year, genre, poster, episode)

        tvShowViewModel.delete(item)
        Toast.makeText(this, "You just remove $title from favorite", Toast.LENGTH_SHORT).show()
    }

    private fun addToFavorite() {
        item = FavoriteTvShow(tvshowId, title, description, year, genre, poster, episode)

        tvShowViewModel.insert(item)
        Toast.makeText(this, "You just add $title to favorite", Toast.LENGTH_SHORT).show()
    }
}