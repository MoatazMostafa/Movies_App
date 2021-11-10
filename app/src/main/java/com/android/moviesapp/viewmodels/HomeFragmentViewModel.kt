package com.android.moviesapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.android.moviesapp.models.Movies
import com.android.moviesapp.models.MoviesResponse
import com.android.moviesapp.models.repository.NetworkRepository

class HomeFragmentViewModel(application: Application) :
    AndroidViewModel(application) {
    var networkRepository = NetworkRepository()
    var nowPlayingMovies: List<Movies>? = null
    var upComingMovies: List<Movies>? = null
    var topRatedMovies: List<Movies>? = null

    fun getNowPlayingMovies(onFinish: (Boolean, MoviesResponse?, String) -> Unit) {
        networkRepository.getNowPlayingMovies(onFinish)
    }

    fun getupComingMovies(onFinish: (Boolean, MoviesResponse?, String) -> Unit) {
        networkRepository.getUpcomingMovies(onFinish)
    }

    fun getTopRatedMovies(onFinish: (Boolean, MoviesResponse?, String) -> Unit) {
        networkRepository.getTopRatedMovies(onFinish)
    }
}

