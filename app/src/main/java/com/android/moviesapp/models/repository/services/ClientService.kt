package com.android.moviesapp.models.repository.services

import com.android.moviesapp.models.MoviesResponse
import com.android.moviesapp.models.repository.Utils.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ClientService {

    companion object {
        fun getClient(): ClientService {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(HttpClient.getClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit.create(ClientService::class.java)
        }
    }
    @GET("now_playing")
    fun getNowPlayingMovies(
            @Query("api_key") apiKey:String,
            @Query("language") language:String,
            @Query("page") page:String
    ): Call<MoviesResponse>

    @GET("upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey:String,
        @Query("language") language:String,
        @Query("page") page:String
    ): Call<MoviesResponse>

    @GET("top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey:String,
        @Query("language") language:String,
        @Query("page") page:String
    ): Call<MoviesResponse>
}