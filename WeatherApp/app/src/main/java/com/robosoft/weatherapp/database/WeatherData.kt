package com.robosoft.weatherapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weatherData_table")
data class WeatherData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo val id: Int?,
    @ColumnInfo val location: String,
    @ColumnInfo val temperature: Double,
    @ColumnInfo val desc: String,
    @ColumnInfo val isFavourite: Boolean
)
