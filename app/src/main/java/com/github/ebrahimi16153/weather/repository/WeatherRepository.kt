package com.github.ebrahimi16153.weather.repository

import com.github.ebrahimi16153.weather.data.DataOrException
import com.github.ebrahimi16153.weather.model.Weather
import com.github.ebrahimi16153.weather.model.WeatherObject
import com.github.ebrahimi16153.weather.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {


    suspend fun getWeather(cityQuery: String): DataOrException<Weather, Boolean, Exception> {

        val response = try {
            api.getWhether(query = cityQuery)
        } catch (e: Exception) {
            return DataOrException(exception = e)
        }
        return DataOrException(data = response)
    }


}