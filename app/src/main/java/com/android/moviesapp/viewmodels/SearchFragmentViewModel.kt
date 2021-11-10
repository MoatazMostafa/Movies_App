package com.android.moviesapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.android.moviesapp.models.Movies
import com.android.moviesapp.models.MoviesResponse
import com.android.moviesapp.models.repository.NetworkRepository

class SearchFragmentViewModel(application: Application) :
    AndroidViewModel(application) {
    var networkRepository = NetworkRepository()
    var foundedMovies: List<Movies>? = null


    fun getMoviesByName(movieName:String,onFinish: (Boolean, MoviesResponse?, String) -> Unit) {
        networkRepository.getMoviesByName(movieName,onFinish)
    }
}

