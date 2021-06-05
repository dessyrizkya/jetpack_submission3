package com.jetpack.lumiere.ui.content.tvshow

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.lumiere.databinding.FragmentTvShowsBinding
import com.jetpack.lumiere.viewmodel.ViewModelFactory

class TvShowsFragment : Fragment() {

    private var gridLayoutManager: GridLayoutManager? = null
    private var tvshowsAdapter : TvShowsAdapter? = null
    private lateinit var fragmentTvshowBinding: FragmentTvShowsBinding

    private lateinit var tvShowsViewModel: TvShowsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentTvshowBinding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return fragmentTvshowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            tvShowsViewModel = ViewModelProvider(this, ViewModelFactory.getInstance())
                .get(TvShowsViewModel::class.java)
            refresh()
        }
    }

    private fun refresh() {
        val config = resources.configuration.orientation
        val grid = if (config == Configuration.ORIENTATION_PORTRAIT)  2 else 5

        gridLayoutManager = GridLayoutManager(context, grid, LinearLayoutManager.VERTICAL, false)

        showLoading(true)
        tvShowsViewModel.getTvShows().observe(viewLifecycleOwner, { tv ->
            if (tv != null) {
                tvShowsViewModel.getGenres().observe(viewLifecycleOwner, { genres ->
                    if (genres != null) {
                        tvshowsAdapter = TvShowsAdapter(tv, genres)
                        with (fragmentTvshowBinding.rvTvshows) {
                            layoutManager = gridLayoutManager
                            setHasFixedSize(true)
                            adapter = tvshowsAdapter
                        }
                        showLoading(false)
                    }
                })
            }
        })
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        refresh()
    }

    private fun showLoading(isLoading: Boolean) {
        fragmentTvshowBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}