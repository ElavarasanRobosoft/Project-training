package com.robosoft.weatherapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WeatherData ::class], version = 1)
abstract class DatabaseWeather: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
    companion object {
        @Volatile
        private var INSTANCE: DatabaseWeather? = null

        fun getDatabase(context: Context): DatabaseWeather {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseWeather::class.java,"weather_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}