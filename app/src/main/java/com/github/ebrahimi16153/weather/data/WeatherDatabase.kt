package com.github.ebrahimi16153.weather.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.ebrahimi16153.weather.model.City
import com.github.ebrahimi16153.weather.model.Favorites
import com.github.ebrahimi16153.weather.model.UnitWeather


@Database(entities = [Favorites::class , UnitWeather::class], version = 2, exportSchema = false)
abstract class WeatherDatabase:RoomDatabase() {

    abstract fun weatherDatabase():WeatherDao


}