package com.jetpack.ui.content.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jetpack.R
import com.jetpack.data.local.entity.TvShowEntity
import com.jetpack.data.source.remote.response.GenresItem
import com.jetpack.databinding.ItemsTvshowBinding
import com.jetpack.ui.content.tvshow.detail.DetailTvShowActivity

class TvShowAdapter(private val listGenre: List<GenresItem>):
    PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {


    inner class TvShowViewHolder(private val binding: ItemsTvshowBinding, private val listGenre: List<GenresItem>) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: TvShowEntity) {
            var genre = tvshow.genre

            for (i in tvshow.genre.indices){
                for (g in listGenre) {
                    if (tvshow.genre[0].toString() == g.genreId.toString()) {
                        genre = g.genre
                        break
                    }
                }
            }

            with(binding) {
                tvTvshowTitle.text = tvshow.title
                tvTvshowGenre.text = genre
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/original/${tvshow.poster.trim()}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_image)
                    .into(imgTvshowPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.EXTRA_TVSHOW, tvshow.tvshowId)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.tvshowId == newItem.tvshowId
            }
            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvShowAdapter.TvShowViewHolder {
        val binding = ItemsTvshowBinding.inflate(LayoutInflater.from(parent.context))
        return TvShowViewHolder(binding, listGenre)
    }

    override fun onBindViewHolder(holder: TvShowAdapter.TvShowViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null) {
            holder.bind(tv)
        }
    }
}