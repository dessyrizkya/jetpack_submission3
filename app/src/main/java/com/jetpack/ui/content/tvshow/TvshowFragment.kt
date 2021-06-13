package com.jetpack.ui.content.tvshow

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.R
import com.jetpack.data.source.remote.response.GenresItem
import com.jetpack.databinding.FragmentMovieBinding
import com.jetpack.databinding.FragmentTvShowBinding
import com.jetpack.ui.content.movie.MovieAdapter
import com.jetpack.ui.content.movie.MovieViewModel
import com.jetpack.vo.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowBinding
    private val viewModel : TvShowViewModel by viewModels()

    private val genre = ArrayList<GenresItem>()
    private lateinit var listGenre : List<GenresItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
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
        observeTvShows()
    }

    private fun observeTvShows() {
        viewModel.getAllTvShows().observe(viewLifecycleOwner, { listMovie ->
            if (listMovie != null) {
                when (listMovie.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        showLoading(false)
                        Log.e("listTvshows", listMovie.data.toString())
                        binding.rvTvshows.adapter?.let { adapter ->
                            when (adapter) {
                                is TvShowAdapter -> {
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

            rvTvshows.apply {
                layoutManager = GridLayoutManager(context, grid, LinearLayoutManager.VERTICAL, false)
                adapter = TvShowAdapter(listGenre)

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