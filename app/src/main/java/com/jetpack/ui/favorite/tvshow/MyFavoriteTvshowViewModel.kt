package com.jetpack.ui.favorite.tvshow

import androidx.lifecycle.ViewModel
import com.jetpack.data.LumiereRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyFavoriteTvshowViewModel @Inject constructor(private val lumiereRepository: LumiereRepository) : ViewModel() {
    val tvshow = lumiereRepository.getAllFavTvShows()
}