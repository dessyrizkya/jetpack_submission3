package com.jetpack.ui.favorite.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jetpack.R
import com.jetpack.data.source.local.entity.TvShowDetailEntity
import com.jetpack.databinding.ItemsTvshowBinding
import com.jetpack.ui.content.tvshow.detail.DetailTvShowActivity

class MyFavoriteTvshowAdapter (private val listTvShow : List<TvShowDetailEntity>) : RecyclerView.Adapter<MyFavoriteTvshowAdapter.TvShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemsTvshowBinding = ItemsTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemsTvshowBinding)
    }

    override fun getItemCount(): Int = listTvShow.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvshow = listTvShow[position]
        holder.bind(tvshow)
    }

    class TvShowViewHolder(private val binding: ItemsTvshowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: TvShowDetailEntity) {
            with(binding) {

                tvTvshowTitle.text = tvshow.title
                tvTvshowGenre.text = tvshow.genre
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/original/${tvshow.poster.trim()}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_image)
                    .into(imgTvshowPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.EXTRA_TVSHOW, tvshow)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

}