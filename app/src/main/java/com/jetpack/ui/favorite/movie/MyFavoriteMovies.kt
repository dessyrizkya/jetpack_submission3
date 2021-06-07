package com.jetpack.ui.favorite.movie

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.data.source.local.entity.MovieEntity
import com.jetpack.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFavoriteMovies : Fragment() {

    private var gridLayoutManager: GridLayoutManager? = null
    private var moviesAdapter : MyFavoriteMovieAdapter? = null
    private lateinit var fragmentMoviesBinding : FragmentMovieBinding

    private val moviesViewModel: MyFavoriteMoviesViewModel by viewModels()
    private lateinit var listMovie:List<MovieEntity>
    private val arraylistMovie = ArrayList<MovieEntity>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMoviesBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            showData()
        }
    }

    private fun showData() {
        val config = resources.configuration.orientation
        val grid = if (config == Configuration.ORIENTATION_PORTRAIT)  2 else 5

        gridLayoutManager = GridLayoutManager(context, grid, LinearLayoutManager.VERTICAL, false)

        showLoading(true)

        activity?.let {
            moviesViewModel.movies.observe(viewLifecycleOwner, { movies ->
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

                    if(listMovie.isNotEmpty()) {
                        showNull(false)
                    } else {
                        showNull(true)
                    }

                    moviesAdapter = MyFavoriteMovieAdapter(listMovie)
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

    private fun showNull(isNull: Boolean) {
        fragmentMoviesBinding.imgEmpty.visibility = if (isNull) View.VISIBLE else View.GONE
    }
}