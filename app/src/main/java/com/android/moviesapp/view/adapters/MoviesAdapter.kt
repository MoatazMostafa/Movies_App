package com.android.moviesapp.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.android.moviesapp.R
import com.android.moviesapp.models.Movies
import com.android.moviesapp.models.repository.Utils.IMAGE_BASE_URL
import com.squareup.picasso.Picasso

class MoviesAdapter(
    private val movies: List<Movies>?,
    var onItemClicked: (Movies) -> Unit
)
    : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieNameTV.text = "${holder.movieNameTV.text} ${movies?.get(position)?.originalTitle}"
        holder.movieReleaseDateTV.text = "${holder.movieReleaseDateTV.text} ${movies?.get(position)?.releaseDate}"
        holder.movieRateTV.text = "${holder.movieRateTV.text} ${movies?.get(position)?.voteAverage}/10"
        Picasso.get().load("$IMAGE_BASE_URL${movies?.get(position)?.posterPath}").into(holder.movieImageIV)
        holder.itemCL.setOnClickListener { onItemClicked.invoke(movies!![position]) }
    }

    override fun getItemCount(): Int {
        if(movies?.size==null)
            return 0
        return movies.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemCL:ConstraintLayout = itemView.findViewById(R.id.item_movie)
        val movieNameTV: TextView = itemView.findViewById(R.id.item_movie_nameTV)
        val movieReleaseDateTV: TextView = itemView.findViewById(R.id.item_movie_release_dateTV)
        val movieRateTV: TextView = itemView.findViewById(R.id.item_movie_rateTV)
        val movieImageIV: ImageView = itemView.findViewById(R.id.item_movie_imageIV)
    }
}
