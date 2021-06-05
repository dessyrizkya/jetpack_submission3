package com.jetpack.lumiere.ui.content.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jetpack.lumiere.R
import com.jetpack.lumiere.data.source.local.entity.TvShowEntity
import com.jetpack.lumiere.data.source.remote.response.GenresItem
import com.jetpack.lumiere.data.source.remote.response.ResultsItem
import com.jetpack.lumiere.data.source.remote.response.TvResultsItem
import com.jetpack.lumiere.data.source.remote.response.TvShowResponse
import com.jetpack.lumiere.databinding.ItemsTvshowBinding
import com.jetpack.lumiere.ui.content.tvshow.detail.DetailTvShowActivity

class TvShowsAdapter(private val listTvShow : List<TvShowEntity>, private val listGenres : List<GenresItem>) : RecyclerView.Adapter<TvShowsAdapter.TvShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemsTvshowBinding = ItemsTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemsTvshowBinding, listGenres)
    }

    override fun getItemCount(): Int = listTvShow.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvshow = listTvShow[position]
        holder.bind(tvshow)
    }

    class TvShowViewHolder(private val binding: ItemsTvshowBinding, private val listGenres : List<GenresItem>) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: TvShowEntity) {
            with(binding) {
                var genre = "tes"

                for (g in listGenres) {
                    if (tvshow.genre.toInt() == g.genreId) {
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

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.EXTRA_TVSHOW, tvshow.tvshowId)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

}