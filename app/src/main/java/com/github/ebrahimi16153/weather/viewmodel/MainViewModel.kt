package com.github.ebrahimi16153.weather.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ebrahimi16153.weather.data.DataOrException
import com.github.ebrahimi16153.weather.model.City
import com.github.ebrahimi16153.weather.model.Weather
import com.github.ebrahimi16153.weather.model.WeatherObject
import com.github.ebrahimi16153.weather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class MainViewModel @Inject constructor(private val repository: WeatherRepository) :
    ViewModel() {

        suspend fun getWeatherData(city:String):DataOrException<Weather,Boolean,Exception>{
            return repository.getWeather(cityQuery = city)
        }


}
