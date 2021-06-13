package com.jetpack.ui.content.movie

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.R
import com.jetpack.data.source.remote.response.GenresItem
import com.jetpack.databinding.FragmentMovieBinding
import com.jetpack.vo.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel : MovieViewModel by viewModels()

    private val genre = ArrayList<GenresItem>()
    private lateinit var listGenre : List<GenresItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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

        Log.e("listGenre", genre.toString())

        showNull(false)
        setRecyclerView()
        observeMovies()
    }

    private fun observeMovies() {
        viewModel.getAllMovies().observe(viewLifecycleOwner, { listMovie ->
            if (listMovie != null) {
                when (listMovie.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        showLoading(false)
                        binding.rvMovies.adapter?.let { adapter ->
                            when (adapter) {
                                is MovieAdapter -> {
                                    adapter.submitList(listMovie.data)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        showNull(true)
                        Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
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

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun showNull(isNull: Boolean) {
        if (isNull) {
            binding.imgEmpty.visibility = View.VISIBLE
        } else {
            binding.imgEmpty.visibility = View.GONE
        }
    }



}