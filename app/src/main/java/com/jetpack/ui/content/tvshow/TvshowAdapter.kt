package com.jetpack.ui.content.tvshow

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jetpack.R
import com.jetpack.data.source.local.entity.TvShowDetailEntity
import com.jetpack.data.source.remote.response.GenresItem
import com.jetpack.data.source.remote.response.TvResultsItem
import com.jetpack.databinding.ItemsTvshowBinding
import com.jetpack.ui.content.tvshow.detail.DetailTvShowActivity

class TvshowAdapter(private val listGenres: List<GenresItem>) : PagingDataAdapter<TvResultsItem, TvshowAdapter.TvViewHolder>(COMPARATOR) {
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<TvResultsItem>() {
            override fun areItemsTheSame(oldItem: TvResultsItem, newItem: TvResultsItem): Boolean =
                oldItem.tvId == newItem.tvId

            override fun areContentsTheSame(oldItem: TvResultsItem, newItem: TvResultsItem): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: TvshowAdapter.TvViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvshowAdapter.TvViewHolder {
        val binding = ItemsTvshowBinding.inflate(LayoutInflater.from(parent.context))
        return TvViewHolder(binding, listGenres)
    }

    inner class TvViewHolder (private val binding: ItemsTvshowBinding, private val listGenres: List<GenresItem>) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: TvResultsItem) {
            with(binding) {
                var genre = "loading..."

                for (g in listGenres) {
                    if (tvshow.genreId[0] == g.genreId) {
                        genre = g.genre
                        break
                    }
                }

                tvTvshowTitle.text = tvshow.title
                tvTvshowGenre.text = genre
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/original/${tvshow.poster.trim()}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_image)
                    .into(imgTvshowPoster)

                val tvshowEntity = TvShowDetailEntity(
                    tvshow.tvId.toString(),
                    tvshow.title,
                    tvshow.description,
                    tvshow.year,
                    genre,
                    tvshow.poster,
                    ""
                )

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.EXTRA_TVSHOW, tvshowEntity)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}