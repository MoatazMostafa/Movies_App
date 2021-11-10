package com.android.moviesapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.android.moviesapp.R
import com.android.moviesapp.models.Movies
import com.android.moviesapp.models.repository.Utils
import com.android.moviesapp.models.repository.Utils.GOOGLE_API
import com.android.moviesapp.viewmodels.MovieViewModel
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity : YouTubeBaseActivity() {
    lateinit var movieViewModel: MovieViewModel
    var onInitializedListener: YouTubePlayer.OnInitializedListener? = null
    @SuppressLint("SetTextI18n", "ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        movieViewModel= MovieViewModel(application)
        movieViewModel.movie=intent.getSerializableExtra("Movie") as Movies

        movie_titleTV.text =  movieViewModel.movie.originalTitle
        movie_release_dateTV.text = "${movie_release_dateTV.text} ${movieViewModel.movie.releaseDate}"
        movie_rateTV.text = "${movie_rateTV.text} ${movieViewModel.movie.voteAverage}"
        movie_overviewTV.text = "${movie_overviewTV.text} ${movieViewModel.movie.overview}"
        Picasso.get().load("${Utils.IMAGE_BASE_URL}${movieViewModel.movie.backdropPath}").into(movie_main_imageIV)
        Picasso.get().load("${Utils.IMAGE_BASE_URL}${movieViewModel.movie.posterPath}").into(movie_poster_imageIV)
        movie_play_buttonIV.setOnClickListener {
            if(onInitializedListener!=null) {
                movie_youTubePlayerView.initialize(GOOGLE_API, onInitializedListener)
                movie_play_buttonIV.visibility=View.GONE
            }
        }

        movieViewModel.getVideos{ isSuccess, videoResponse, _ ->
            if (isSuccess) {
                val videoList=videoResponse?.results?.filter { it.site=="YouTube" && it.type == "Trailer"}
                if(videoList!!.isNotEmpty()) {
                    onInitializedListener = object : YouTubePlayer.OnInitializedListener {
                        override fun onInitializationSuccess(
                            provider: YouTubePlayer.Provider,
                            youTubePlayer: YouTubePlayer,
                            b: Boolean
                        ) {
                            youTubePlayer.loadVideo(videoList[0].key)
                        }

                        override fun onInitializationFailure(
                            provider: YouTubePlayer.Provider,
                            youTubeInitializationResult: YouTubeInitializationResult
                        ) {
                        }
                    }
                }
            }
        }
    }
}