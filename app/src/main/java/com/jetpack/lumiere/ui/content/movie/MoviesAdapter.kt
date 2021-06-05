package com.jetpack.lumiere.ui.content.movie

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jetpack.lumiere.R
import com.jetpack.lumiere.data.source.local.entity.MovieEntity
import com.jetpack.lumiere.data.source.remote.response.GenresItem
import com.jetpack.lumiere.data.source.remote.response.ResultsItem
import com.jetpack.lumiere.databinding.ItemsMovieBinding
import com.jetpack.lumiere.ui.content.movie.detail.DetailMovieActivity

class MoviesAdapter(private val listMovie : List<MovieEntity>, private val listGenres : List<GenresItem>?) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding, listGenres)
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }

    class MovieViewHolder(private val binding: ItemsMovieBinding, private val listGenres : List<GenresItem>?) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {

            with(binding) {
                var genre = "tes"

                if (listGenres != null) {
                    for (i in movie.genre.indices){
                        for (g in listGenres) {
                            if (movie.genre.toInt() == g.genreId) {
                                genre = g.genre
                                break
                            }
                        }
                    }
                } else {
                    genre = movie.genre
                }


                tvMovieTitle.text = movie.title
                tvMovieGenre.text = genre
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/original/${movie.poster.trim()}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_image)
                    .into(imgPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie.movieId.toString())
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

}