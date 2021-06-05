package com.jetpack.lumiere.ui.content.favorite.content.tvshow

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.lumiere.data.source.local.entity.MovieEntity
import com.jetpack.lumiere.data.source.local.entity.TvShowDetailEntity
import com.jetpack.lumiere.data.source.local.entity.TvShowEntity
import com.jetpack.lumiere.databinding.FragmentMoviesBinding
import com.jetpack.lumiere.databinding.FragmentTvShowsBinding
import com.jetpack.lumiere.ui.content.favorite.content.movie.MyFavoriteMoviesViewModel
import com.jetpack.lumiere.ui.content.movie.MoviesAdapter
import com.jetpack.lumiere.ui.content.tvshow.TvShowsAdapter
import com.jetpack.lumiere.viewmodel.ViewModelFactory

class MyFavoriteTvShow : Fragment() {

    private var gridLayoutManager: GridLayoutManager? = null
    private var tvshowsAdapter : TvShowsAdapter? = null
    private lateinit var fragmentTvShowsBinding : FragmentTvShowsBinding

    private lateinit var moviesViewModel: MyFavoriteTvShowsViewModel
    private lateinit var listMovie:List<TvShowEntity>
    private val arraylistMovie = ArrayList<TvShowEntity>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentTvShowsBinding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            moviesViewModel = ViewModelProvider(this, ViewModelFactory.getInstance())
                .get(MyFavoriteTvShowsViewModel::class.java)
            refresh()
        }
    }

    private fun refresh() {
        val config = resources.configuration.orientation
        val grid = if (config == Configuration.ORIENTATION_PORTRAIT)  2 else 5

        gridLayoutManager = GridLayoutManager(context, grid, LinearLayoutManager.VERTICAL, false)

        showLoading(true)
        activity?.let {
            moviesViewModel.getAllFavorite(it.application).observe(viewLifecycleOwner, { tv ->
                arraylistMovie.clear()
                if (tv != null) {
                    for (m in tv) {
                        val movie = TvShowEntity(m.tvshow_id,
                            m.title.toString(),
                            m.description.toString(),
                            m.year.toString(),
                            m.genre.toString(),
                            m.poster.toString())
                        arraylistMovie.add(movie)
                    }
                    listMovie = arraylistMovie


                    tvshowsAdapter = TvShowsAdapter(listMovie, null)
                    with(fragmentTvShowsBinding.rvTvshows) {
                        layoutManager = gridLayoutManager
                        setHasFixedSize(true)
                        adapter = tvshowsAdapter
                    }
                    showLoading(false)
                }
            })
        }
    }


    private fun showLoading(isLoading: Boolean) {
        fragmentTvShowsBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}