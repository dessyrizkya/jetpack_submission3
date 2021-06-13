package com.jetpack.ui.content.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jetpack.R
import com.jetpack.data.local.entity.MovieEntity
import com.jetpack.data.source.remote.response.GenresItem
import com.jetpack.databinding.ItemsMovieBinding
import com.jetpack.ui.content.movie.detail.DetailMovieActivity

class MovieAdapter(private val listGenre: List<GenresItem>) : PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder {
        val binding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context))
        return MovieViewHolder(binding, listGenre)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    inner class MovieViewHolder(private val binding: ItemsMovieBinding, private val listGenre: List<GenresItem>) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            var genre = movie.genre

            for (i in movie.genre.indices){
                for (g in listGenre) {
                    if (movie.genre[0].toString() == g.genreId.toString()) {
                        genre = g.genre
                        break
                    }
                }
            }

            with(binding) {
                tvMovieTitle.text = movie.title
                tvMovieGenre.text = genre

                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/original/${movie.poster.trim()}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_image)
                    .into(imgPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie.movieId)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}
