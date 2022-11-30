package com.github.ebrahimi16153.weather.network

import com.github.ebrahimi16153.weather.model.WeatherObject
import com.github.ebrahimi16153.weather.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface WeatherApi {

    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWhether(
        @Query(value = "q") query: String,
        @Query(value = "units") unit: String = "metric",
        @Query(value = "appid" ) appid:String = Constants.API_KEY
    ): WeatherObject


}
