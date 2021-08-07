package com.binarteamtwo.binarvideoplayer.data.network.services

import com.binarteamtwo.binarvideoplayer.BuildConfig
import com.binarteamtwo.binarvideoplayer.data.constant.Constant
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.MovieDetail
import com.binarteamtwo.binarvideoplayer.data.network.entity.response.MovieResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface MovieDetailApiServices {


    @GET("movie/")
    suspend fun getMovieDetails(
        @Query("id")movieId : String,
        @Query("api-key")apiKeys: String = BuildConfig.API_KEY_THEMOVIE_DB,
        @Query("append-to-response")appendToResponse : String = Constant.APPEND_TO_RESPONSE
    ) : MovieDetail

    companion object{
        private var retrofitServices : MovieDetailApiServices? = null
        fun getInstance() : MovieDetailApiServices?{
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
                retrofitServices = retrofit.create(MovieDetailApiServices::class.java)
            }
            return retrofitServices
        }
    }



}


