package com.android.moviesapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.android.moviesapp.models.Movies
import com.android.moviesapp.models.VideoResponse
import com.android.moviesapp.models.repository.NetworkRepository



class MovieViewModel(application: Application) :
    AndroidViewModel(application) {
    var networkRepository = NetworkRepository()
    lateinit var movie:Movies

    fun getVideos(onFinish: (Boolean, VideoResponse?, String) -> Unit) {
        networkRepository.getVideos(movie.id.toString(),onFinish)
    }


}
