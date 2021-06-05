package com.jetpack.lumiere.ui.content.favorite.content

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.lumiere.R
import com.jetpack.lumiere.data.source.local.entity.MovieEntity
import com.jetpack.lumiere.data.source.remote.response.GenresItem
import com.jetpack.lumiere.databinding.FragmentMoviesBinding
import com.jetpack.lumiere.ui.content.movie.MoviesAdapter
import com.jetpack.lumiere.ui.content.movie.MoviesViewModel
import com.jetpack.lumiere.viewmodel.ViewModelFactory


class MyFavoriteMovies : Fragment() {

    private var gridLayoutManager: GridLayoutManager? = null
    private var moviesAdapter : MoviesAdapter? = null
    private lateinit var fragmentMoviesBinding : FragmentMoviesBinding

    private lateinit var moviesViewModel: MyFavoriteMoviesViewModel
    private lateinit var listMovie:List<MovieEntity>
    private val arraylistMovie = ArrayList<MovieEntity>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            moviesViewModel = ViewModelProvider(this, ViewModelFactory.getInstance())
                .get(MyFavoriteMoviesViewModel::class.java)
            refresh()
        }
    }

    private fun refresh() {
        val config = resources.configuration.orientation
        val grid = if (config == Configuration.ORIENTATION_PORTRAIT)  2 else 5

        gridLayoutManager = GridLayoutManager(context, grid, LinearLayoutManager.VERTICAL, false)

        showLoading(true)

        activity?.let {
            moviesViewModel.getAllFavorite(it.application).observe(viewLifecycleOwner, { movies ->
                arraylistMovie.clear()
                if (movies != null) {
                    for (m in movies) {
                        val movie = MovieEntity(m.movie_id,
                            m.title.toString(),
                            m.description.toString(),
                            m.year.toString(),
                            m.genre.toString(),
                            m.poster.toString())
                        arraylistMovie.add(movie)
                    }
                    listMovie = arraylistMovie

                    moviesAdapter = MoviesAdapter(listMovie, null)
                    with (fragmentMoviesBinding.rvMovies) {
                        layoutManager = gridLayoutManager
                        setHasFixedSize(true)
                        adapter = moviesAdapter
                        showLoading(false)
                    }
                }
            })
        }
    }

    private fun showLoading(isLoading: Boolean) {
        fragmentMoviesBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}