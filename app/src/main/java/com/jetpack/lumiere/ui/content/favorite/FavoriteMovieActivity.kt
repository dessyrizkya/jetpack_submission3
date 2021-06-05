package com.jetpack.lumiere.ui.content.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jetpack.lumiere.R
import com.jetpack.lumiere.databinding.ActivityFavoriteMovieBinding
import com.jetpack.lumiere.ui.home.SectionsPagerAdapter

class FavoriteMovieActivity : AppCompatActivity() {

    companion object {
        private val TAB_ICONS = intArrayOf(R.drawable.ic_film, R.drawable.ic_tv)
    }

    private lateinit var activityFavoriteMovieBinding: ActivityFavoriteMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityFavoriteMovieBinding = ActivityFavoriteMovieBinding.inflate(layoutInflater)
        setContentView(activityFavoriteMovieBinding.root)

        setSupportActionBar(activityFavoriteMovieBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sectionsPagerAdapter = FavoriteSectionsPagerAdapter(this, supportFragmentManager)
        activityFavoriteMovieBinding.viewPager.adapter = sectionsPagerAdapter
        activityFavoriteMovieBinding.tabs.setupWithViewPager(activityFavoriteMovieBinding.viewPager)

        activityFavoriteMovieBinding.tabs.getTabAt(1)?.setIcon(TAB_ICONS[1])
        activityFavoriteMovieBinding.tabs.getTabAt(0)?.setIcon(TAB_ICONS[0])

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}