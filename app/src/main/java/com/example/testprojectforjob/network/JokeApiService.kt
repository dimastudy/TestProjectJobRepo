package com.example.testprojectforjob.network

import androidx.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Query


interface JokeApiService {

    companion object {
        val BASE_URL = "https://api.chucknorris.io/"
    }


    @GET("jokes/categories")
    suspend fun getCategories() : List<String>

    @GET("jokes/random")
    suspend fun getJokeByCategory(@Query("category") category: String): JokeNetwork
}