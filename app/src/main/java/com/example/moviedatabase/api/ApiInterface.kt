package com.example.moviedatabase.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/")
    fun searchMovie(@Query("t")t:String,
               @Query("apikey")apikey:String):retrofit2.Call<MoviesDetailsModel>
}