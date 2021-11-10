package com.android.moviesapp.models.repository.services

import com.android.moviesapp.models.MoviesResponse
import com.android.moviesapp.models.VideoResponse
import com.android.moviesapp.models.repository.Utils.BASE_URL
import com.android.moviesapp.models.repository.Utils.MOVIE_URL
import com.android.moviesapp.models.repository.Utils.SEARCH_URL
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


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
    @GET("${MOVIE_URL}/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
    ): Call<MoviesResponse>

    @GET("${MOVIE_URL}/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
    ): Call<MoviesResponse>

    @GET("${MOVIE_URL}/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
    ): Call<MoviesResponse>

    @GET(SEARCH_URL)
    fun getMoviesByName(
        @Query("api_key") apiKey: String,
        @Query("query") movieName: String,
    ): Call<MoviesResponse>

    @GET
    fun getVideos(
        @Url url: String?,
        @Query("api_key") apiKey: String
    ): Call<VideoResponse>
}