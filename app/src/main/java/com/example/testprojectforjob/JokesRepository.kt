package com.example.testprojectforjob

import com.example.testprojectforjob.network.JokeApiService
import com.example.testprojectforjob.network.JokeNetwork
import javax.inject.Inject


class JokesRepository @Inject constructor(
    private val jokeApi: JokeApiService
) {

    suspend fun getCategoriesFromNetwork() : List<String> =
        jokeApi.getCategories()


    suspend fun getJokeFromNetwork(category: String) : JokeNetwork =
        jokeApi.getJokeByCategory(category)


}