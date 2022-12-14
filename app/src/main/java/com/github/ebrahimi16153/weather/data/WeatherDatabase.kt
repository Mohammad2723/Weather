package com.github.ebrahimi16153.weather.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.ebrahimi16153.weather.model.City
import com.github.ebrahimi16153.weather.model.Favorites


@Database(entities = [Favorites::class], version = 1, exportSchema = false)
abstract class WeatherDatabase:RoomDatabase() {

    abstract fun weatherDatabase():WeatherDao


}