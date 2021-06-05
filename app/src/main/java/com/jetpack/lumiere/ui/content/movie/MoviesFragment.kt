package com.jetpack.lumiere.ui.content.movie

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.lumiere.databinding.FragmentMoviesBinding
import com.jetpack.lumiere.viewmodel.ViewModelFactory


class MoviesFragment : Fragment() {

    private var gridLayoutManager: GridLayoutManager? = null
    private var moviesAdapter : MoviesAdapter? = null
    private lateinit var fragmentMoviesBinding : FragmentMoviesBinding
    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            moviesViewModel = ViewModelProvider(this, ViewModelFactory.getInstance())
                .get(MoviesViewModel::class.java)

            refresh()

        }

    }

    private fun refresh() {
        val config = resources.configuration.orientation
        val grid = if (config == Configuration.ORIENTATION_PORTRAIT)  2 else 5

        gridLayoutManager = GridLayoutManager(context, grid, LinearLayoutManager.VERTICAL, false)

        showLoading(true)
        moviesViewModel.getMovies().observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                moviesViewModel.getGenres().observe(viewLifecycleOwner, { genres ->
                    if (genres != null) {
                        moviesAdapter = MoviesAdapter(movies, genres)
                        with (fragmentMoviesBinding.rvMovies) {
                            layoutManager = gridLayoutManager
                            setHasFixedSize(true)
                            adapter = moviesAdapter
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
        fragmentMoviesBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}