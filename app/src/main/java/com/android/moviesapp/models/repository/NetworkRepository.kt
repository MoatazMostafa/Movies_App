package com.android.moviesapp.models.repository


import com.android.moviesapp.models.MoviesResponse
import com.android.moviesapp.models.VideoResponse
import com.android.moviesapp.models.repository.Utils.API_KEY
import com.android.moviesapp.models.repository.Utils.BASE_URL
import com.android.moviesapp.models.repository.Utils.MOVIE_URL
import com.android.moviesapp.models.repository.services.ClientService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkRepository {
    var clientService = ClientService.getClient()

    fun getNowPlayingMovies(onFinish: (Boolean,MoviesResponse?,String) -> Unit) {
        clientService.getNowPlayingMovies(API_KEY   ).enqueue(object :
            Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    onFinish.invoke(true,response.body(),"")
                } else {
                    onFinish.invoke(false, null,"Internal Server Error")
                }
            }
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                onFinish.invoke(false, null,"Connection Error")
            }
        })
    }

    fun getUpcomingMovies(onFinish: (Boolean,MoviesResponse?,String) -> Unit) {
        clientService.getUpcomingMovies(API_KEY).enqueue(object :
            Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    onFinish.invoke(true,response.body(),"")
                } else {
                    onFinish.invoke(false, null,"Internal Server Error")
                }
            }
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                onFinish.invoke(false, null,"Check internet and try again")
            }
        })
    }

    fun getTopRatedMovies(onFinish: (Boolean,MoviesResponse?,String) -> Unit) {
        clientService.getTopRatedMovies(API_KEY).enqueue(object :
            Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    onFinish.invoke(true,response.body(),"")
                } else {
                    onFinish.invoke(false, null,"Internal Server Error")
                }
            }
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                onFinish.invoke(false, null,"Connection Error")
            }
        })
    }

    fun getMoviesByName(movieName: String, onFinish: (Boolean, MoviesResponse?, String) -> Unit) {
        clientService.getMoviesByName(API_KEY, movieName).enqueue(object :
            Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    onFinish.invoke(true,response.body(),"")
                } else {
                    onFinish.invoke(false, null,"Internal Server Error")
                }
            }
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                onFinish.invoke(false, null,"Connection Error")
            }
        })
    }

    fun getVideos(movieId: String, onFinish: (Boolean, VideoResponse?, String) -> Unit) {
        clientService.getVideos("$BASE_URL$MOVIE_URL/$movieId/videos",API_KEY).enqueue(object :
            Callback<VideoResponse> {
            override fun onResponse(
                call: Call<VideoResponse>,
                response: Response<VideoResponse>
            ) {
                if (response.isSuccessful) {
                    onFinish.invoke(true,response.body(),"")
                } else {
                    onFinish.invoke(false, null,"Internal Server Error")
                }
            }
            override fun onFailure(call: Call<VideoResponse>, t: Throwable) {
                onFinish.invoke(false, null,"Connection Error")
            }
        })
    }
}