package com.jetpack.lumiere.ui.content.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jetpack.lumiere.R
import com.jetpack.lumiere.ui.content.favorite.content.movie.MyFavoriteMovies
import com.jetpack.lumiere.ui.content.favorite.content.tvshow.MyFavoriteTvShow
import com.jetpack.lumiere.ui.content.tvshow.TvShowsFragment

class FavoriteSectionsPagerAdapter (private val mContext: Context, fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv_shows)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MyFavoriteMovies()
            1 -> MyFavoriteTvShow()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? = mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 2
}