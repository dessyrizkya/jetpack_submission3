package com.jetpack.ui.favorite.tvshow

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.data.source.remote.response.GenresItem
import com.jetpack.databinding.FragmentTvShowBinding
import com.jetpack.ui.content.tvshow.TvShowAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteTvShow : Fragment() {
    private val viewModel : FavoriteTvshowViewModel by viewModels()
    private lateinit var binding: FragmentTvShowBinding

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
        viewModel.getFavTvshows().observe(viewLifecycleOwner, { listTv ->
            if (listTv != null) {
                binding.rvTvshows.adapter?.let { adapter ->
                    when (adapter) {
                        is TvShowAdapter -> {
                            if (listTv.isNullOrEmpty()) {
                                showNull(true)
                            } else {
                                adapter.submitList(listTv)
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

            rvTvshows.apply {
                layoutManager = GridLayoutManager(context, grid, LinearLayoutManager.VERTICAL, false)
                adapter = TvShowAdapter(listGenre)
            }
        }
    }

    private fun showNull(isNull: Boolean) {
        if (isNull) {
            binding.imgEmpty.visibility = View.VISIBLE
            binding.rvTvshows.visibility = View.GONE
        } else {
            binding.imgEmpty.visibility = View.GONE
        }
    }

}