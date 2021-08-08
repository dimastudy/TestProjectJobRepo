package com.example.testprojectforjob.network


import com.google.gson.annotations.SerializedName

data class JokeNetwork(
    @SerializedName("value")
    val value: String
)