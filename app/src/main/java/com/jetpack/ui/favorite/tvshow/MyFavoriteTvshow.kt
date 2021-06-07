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
import com.jetpack.data.source.local.entity.TvShowDetailEntity
import com.jetpack.databinding.FragmentTvshowBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFavoriteTvshow : Fragment() {

    private var gridLayoutManager: GridLayoutManager? = null
    private var tvshowAdapter : MyFavoriteTvshowAdapter? = null
    private lateinit var fragmentTvBinding : FragmentTvshowBinding

    private val tvshowViewModel: MyFavoriteTvshowViewModel by viewModels()
    private lateinit var listTvshow:List<TvShowDetailEntity>
    private val arraylistTvshow = ArrayList<TvShowDetailEntity>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentTvBinding = FragmentTvshowBinding.inflate(layoutInflater, container, false)
        return fragmentTvBinding.root
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
            tvshowViewModel.tvshow.observe(viewLifecycleOwner, { tv ->
                arraylistTvshow.clear()
                if (tv != null) {
                    for (t in tv) {
                        val tvshow = TvShowDetailEntity(t.tvshow_id,
                            t.title.toString(),
                            t.description.toString(),
                            t.year.toString(),
                            t.genre.toString(),
                            t.poster.toString(),
                            t.episode.toString())
                        arraylistTvshow.add(tvshow)
                    }

                    listTvshow = arraylistTvshow

                    if(listTvshow.isNotEmpty()) {
                        showNull(false)
                    } else {
                        showNull(true)
                    }

                    tvshowAdapter = MyFavoriteTvshowAdapter(listTvshow)
                    with (fragmentTvBinding.rvTvshows) {
                        layoutManager = gridLayoutManager
                        setHasFixedSize(true)
                        adapter = tvshowAdapter
                        showLoading(false)
                    }
                }
            })
        }
    }

    private fun showLoading(isLoading: Boolean) {
        fragmentTvBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showNull(isNull: Boolean) {
        fragmentTvBinding.imgEmpty.visibility = if (isNull) View.VISIBLE else View.GONE
    }
}