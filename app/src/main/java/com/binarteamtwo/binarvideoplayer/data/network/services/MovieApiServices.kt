package com.binarteamtwo.binarvideoplayer.data.network.services

import com.binarteamtwo.binarvideoplayer.BuildConfig
import com.binarteamtwo.binarvideoplayer.data.constant.Constant
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.Movie
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.MovieResponse
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.MovieTrailer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface MovieApiServices {

    @GET("movie/popular")
    suspend fun getMoviePopular(
        @Query("api_key")apiKeys : String = BuildConfig.API_KEY_THEMOVIE_DB,
        @Query("language")language : String = Constant.MOVIE_LANGUAGE,
        @Query("page-size")pageSize : Int = Constant.PAGE_SIZE
    ): MovieResponse

    @GET("/3/discover/movie")
    suspend fun getMovie(
        @Query("api_key")apiKeys: String = BuildConfig.API_KEY_THEMOVIE_DB,
        @Query("language")language: String = Constant.MOVIE_LANGUAGE,
        @Query("sort_by")sortBy : String = "release_date.desc",
        @Query("year") year : String = "2021"
    ) : MovieResponse


    @GET("movie/{id}/videos")
    suspend fun getMovieTrailer(
        @Path("id")id: Int,
        @Query("api_key")apiKeys: String = BuildConfig.API_KEY_THEMOVIE_DB,

        ): MovieTrailer

    companion object{
        private var retrofitServices : MovieApiServices? = null
        fun getInstance() : MovieApiServices?{
            if(retrofitServices == null){
                //initialize
                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL_THEMOVIE_DB)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                retrofitServices = retrofit.create(MovieApiServices::class.java)
            }
            return retrofitServices
        }
    }



}


