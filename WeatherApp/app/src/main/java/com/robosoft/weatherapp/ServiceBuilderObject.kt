package com.robosoft.weatherapp

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilderObject {
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun reterofitService(): RetrofitApi? {
        return retrofit.create(RetrofitApi::class.java)
    }
}