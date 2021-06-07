package com.jetpack.ui.content.tvshow

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.R
import com.jetpack.data.source.remote.response.GenresItem
import com.jetpack.databinding.FragmentMovieBinding
import com.jetpack.databinding.FragmentTvshowBinding
import com.jetpack.ui.content.MovieLoadStateAdapter
import com.jetpack.ui.content.movie.MovieAdapter
import com.jetpack.ui.content.movie.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TvshowFragment : Fragment() {

    private lateinit var fragmentTvBinding : FragmentTvshowBinding
    private val tvshowViewModel : TvshowViewModel by viewModels()
    private var gridLayoutManager: GridLayoutManager? = null
    private lateinit var adapter: TvshowAdapter

    private val genre = ArrayList<GenresItem>()
    private lateinit var listGenre : List<GenresItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentTvBinding = FragmentTvshowBinding.inflate(layoutInflater, container, false)
        return fragmentTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvshowViewModel.getGenres().observe(viewLifecycleOwner, { genres ->
            genre.clear()
            if (genres != null) {
                genre.addAll(genres)
            }
        })

        listGenre = genre

        adapter = TvshowAdapter(listGenre)

        with(fragmentTvBinding) {

            setGrid()

            rvTvshows.setHasFixedSize(true)
            rvTvshows.adapter = adapter.withLoadStateHeaderAndFooter(
                header = MovieLoadStateAdapter{adapter.retry()},
                footer = MovieLoadStateAdapter{adapter.retry()}
            )
            btnTryAgain.setOnClickListener {
                adapter.retry()
            }
        }

        tvshowViewModel.tvshows.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener { loadState ->
            fragmentTvBinding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                rvTvshows.isVisible = loadState.source.refresh is LoadState.NotLoading
                btnTryAgain.isVisible =loadState.source.refresh is LoadState.Error
                tvFailed.isVisible = loadState.source.refresh is LoadState.Error
                imgEmpty.isVisible = loadState.source.refresh is LoadState.Error
            }
        }
    }

    private fun setGrid() {
        val config = resources.configuration.orientation
        val grid = if (config == Configuration.ORIENTATION_PORTRAIT)  2 else 5

        gridLayoutManager = GridLayoutManager(context, grid, LinearLayoutManager.VERTICAL, false)
        fragmentTvBinding.rvTvshows.layoutManager = gridLayoutManager
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setGrid()
    }
}