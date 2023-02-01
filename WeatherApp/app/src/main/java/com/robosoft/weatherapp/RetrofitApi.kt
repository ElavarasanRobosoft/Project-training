package com.robosoft.weatherapp

import com.robosoft.weatherapp.model.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("data/2.5/weather?&appid=c1ce11af5f5e6b87d93ee0f41f03fc9&units=metric")
    fun getData(@Query("q") city:String): retrofit2.Call<ApiResult>
}