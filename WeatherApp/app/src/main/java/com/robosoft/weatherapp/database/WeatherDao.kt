package com.robosoft.weatherapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeatherDao {
    @Insert
    fun insertData(data: WeatherData)

    @Query("SELECT * FROM weatherData_table ORDER BY id DESC")
    fun getWeatherData(): List<WeatherData>

    @Query("SELECT * FROM weatherData_table WHERE isFavourite is 1")
    fun getFavData(): List<WeatherData>

    @Query("UPDATE weatherData_table SET isFavourite = 1 WHERE id=:id")
    fun setFavourite(id: Int)

    @Query("UPDATE weatherData_table SET isFavourite = 0 WHERE id=:id")
    fun removeFavourite(id: Int)

//    @Query("SELECT isFavourite from weatherData_table WHERE id =:id")
//    fun isFavourite(id: Int)
}