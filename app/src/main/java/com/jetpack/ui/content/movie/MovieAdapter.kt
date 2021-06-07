package com.jetpack.ui.content.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jetpack.R
import com.jetpack.data.source.local.entity.MovieEntity
import com.jetpack.data.source.remote.response.GenresItem
import com.jetpack.data.source.remote.response.ResultsItem
import com.jetpack.databinding.ItemsMovieBinding
import com.jetpack.ui.content.movie.detail.DetailMovieActivity

class MovieAdapter(private val listGenres: List<GenresItem>?) : PagingDataAdapter<ResultsItem, MovieAdapter.MovieViewHolder>(COMPARATOR) {
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<ResultsItem>() {
            override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean =
                oldItem.movieId == newItem.movieId

            override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MovieViewHolder {
        val binding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context))
        return MovieViewHolder(binding, listGenres)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class MovieViewHolder(private val binding: ItemsMovieBinding, private val listGenres: List<GenresItem>?) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: ResultsItem) {

            with(binding) {
                var genre = "loading..."

                if (listGenres != null) {
                    for (i in movie.genre.indices){
                        for (g in listGenres) {
                            if (movie.genre[0] == g.genreId) {
                                genre = g.genre
                                break
                            }
                        }
                    }
                } else {
                    genre = movie.genre.toString()
                }

                tvMovieTitle.text = movie.title
                tvMovieGenre.text = genre

                val movieEntity = MovieEntity(
                    movie.movieId.toString(),
                    movie.title,
                    movie.description,
                    movie.year,
                    genre,
                    movie.poster
                )

                if (movie.poster != null) {
                    Glide.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/original/${movie.poster.trim()}")
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                        .error(R.drawable.ic_image)
                        .into(imgPoster)
                }

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movieEntity)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}