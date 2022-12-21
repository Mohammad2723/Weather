package com.github.ebrahimi16153.weather.viewmodel

import androidx.lifecycle.ViewModel
import com.github.ebrahimi16153.weather.data.DataOrException
import com.github.ebrahimi16153.weather.model.Weather
import com.github.ebrahimi16153.weather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
public class MainViewModel @Inject constructor(private val repository: WeatherRepository) :
    ViewModel() {

        suspend fun getWeatherData(city:String , unit:String):DataOrException<Weather,Boolean,Exception>{
            return repository.getWeather(cityQuery = city , unit = unit)
        }


}
