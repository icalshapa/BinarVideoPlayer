package com.binarteamtwo.binarvideoplayer.data.network.services

import com.binarteamtwo.binarvideoplayer.BuildConfig
import com.binarteamtwo.binarvideoplayer.data.constant.Constant
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.Movie
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.MovieResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface MovieApiServices {

    @GET("movie/popular")
    suspend fun getMovie(
        @Query("api_key")apiKeys : String = BuildConfig.API_KEY_THEMOVIE_DB,
        @Query("language")language : String = Constant.MOVIE_LANGUAGE,
        @Query("page-size")pageSize : Int = Constant.PAGE_SIZE
    ): MovieResponse

    @GET("movie/")
    suspend fun getMovieTrailer(
        @Query("id")movieId : String,
        @Query("api_key")apiKeys: String = BuildConfig.API_KEY_THEMOVIE_DB,

        ): MovieResponse

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


