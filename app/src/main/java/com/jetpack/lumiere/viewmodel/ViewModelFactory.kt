package com.jetpack.lumiere.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jetpack.lumiere.data.source.LumiereRepository
import com.jetpack.lumiere.di.Injection
import com.jetpack.lumiere.ui.content.favorite.content.movie.MyFavoriteMoviesViewModel
import com.jetpack.lumiere.ui.content.favorite.content.tvshow.MyFavoriteTvShowsViewModel
import com.jetpack.lumiere.ui.content.movie.MoviesViewModel
import com.jetpack.lumiere.ui.content.tvshow.TvShowsViewModel

class ViewModelFactory private constructor(private val repository: LumiereRepository): ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideLumiereRepository())
            }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(repository) as T
            }

            modelClass.isAssignableFrom(TvShowsViewModel::class.java) -> {
                TvShowsViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MyFavoriteMoviesViewModel::class.java) -> {
                MyFavoriteMoviesViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MyFavoriteTvShowsViewModel::class.java) -> {
                MyFavoriteTvShowsViewModel(repository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}