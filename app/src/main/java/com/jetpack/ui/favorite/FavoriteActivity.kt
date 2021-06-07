package com.jetpack.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jetpack.R
import com.jetpack.databinding.ActivityFavoriteBinding
import com.jetpack.ui.home.SectionsPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {
    private lateinit var activityFavoriteMovieBinding: ActivityFavoriteBinding

    companion object {
        private val TAB_ICONS = intArrayOf(R.drawable.ic_film, R.drawable.ic_tv)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityFavoriteMovieBinding = ActivityFavoriteBinding.inflate(layoutInflater)
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