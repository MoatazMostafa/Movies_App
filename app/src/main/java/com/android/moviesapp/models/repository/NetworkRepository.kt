package com.android.moviesapp.models.repository


import com.android.moviesapp.models.MoviesResponse
import com.android.moviesapp.models.repository.Utils.API_KEY
import com.android.moviesapp.models.repository.services.ClientService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkRepository () {
    var clientService = ClientService.getClient()

    fun getNowPlayingMovies(onFinish: (Boolean,MoviesResponse?,String) -> Unit) {
        clientService.getNowPlayingMovies(API_KEY, "en-US","1").enqueue(object :
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
        clientService.getUpcomingMovies(API_KEY, "en-US","1").enqueue(object :
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
        clientService.getTopRatedMovies(API_KEY, "en-US","1").enqueue(object :
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
}