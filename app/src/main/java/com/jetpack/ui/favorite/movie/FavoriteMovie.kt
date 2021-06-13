package com.jetpack.ui.favorite.movie

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.data.source.remote.response.GenresItem
import com.jetpack.databinding.FragmentMovieBinding
import com.jetpack.ui.content.movie.MovieAdapter
import com.jetpack.ui.content.movie.MovieViewModel
import com.jetpack.vo.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteMovie : Fragment() {

    private val viewModel : FavoriteMovieViewModel by viewModels()
    private lateinit var binding: FragmentMovieBinding

    private val genre = ArrayList<GenresItem>()
    private lateinit var listGenre : List<GenresItem>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getGenres().observe(viewLifecycleOwner, { genres ->
            genre.clear()
            if (genres != null) {
                genre.addAll(genres)
            }
        })

        listGenre = genre

        showNull(false)
        setRecyclerView()
        observeMovies()
    }

    private fun observeMovies() {
        viewModel.getFavMovies().observe(viewLifecycleOwner, { listMovie ->
            if (listMovie != null) {
                binding.rvMovies.adapter?.let { adapter ->
                    when (adapter) {
                        is MovieAdapter -> {
                            if (listMovie.isNullOrEmpty()) {
                                showNull(true)
                            } else {
                                adapter.submitList(listMovie)
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            } else {
                showNull(true)
            }
        })
    }

    private fun setRecyclerView() {
        with(binding) {
            val config = resources.configuration.orientation
            val grid = if (config == Configuration.ORIENTATION_PORTRAIT)  2 else 5

            rvMovies.apply {
                layoutManager = GridLayoutManager(context, grid, LinearLayoutManager.VERTICAL, false)
                adapter = MovieAdapter(listGenre)

            }
        }
    }

    private fun showNull(isNull: Boolean) {
        if (isNull) {
            binding.imgEmpty.visibility = View.VISIBLE
            binding.rvMovies.visibility = View.GONE
        } else {
            binding.imgEmpty.visibility = View.GONE
        }
    }
}