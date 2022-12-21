package com.github.ebrahimi16153.weather.repository

import android.util.Log
import com.github.ebrahimi16153.weather.data.DataOrException
import com.github.ebrahimi16153.weather.model.Weather
import com.github.ebrahimi16153.weather.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {


    suspend fun getWeather(cityQuery: String, unit: String): DataOrException<Weather, Boolean, Exception> {

        val response = try {
            api.getWhether(query = cityQuery , unit = unit)
        } catch (e: Exception) {
            Log.d("ERROR","getWeather $e")
            return DataOrException(exception = e)
        }
        Log.d("RESPONSE","getWeather $response")
        return DataOrException(data = response)
    }


}