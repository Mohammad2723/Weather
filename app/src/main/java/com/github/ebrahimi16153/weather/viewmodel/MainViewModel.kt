package com.github.ebrahimi16153.weather.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ebrahimi16153.weather.data.DataOrException
import com.github.ebrahimi16153.weather.model.Weather
import com.github.ebrahimi16153.weather.model.WeatherObject
import com.github.ebrahimi16153.weather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class MainViewModel @Inject constructor(private val repository: WeatherRepository) :
    ViewModel() {


    val data: MutableState<DataOrException<Weather, Boolean, Exception>>
    = mutableStateOf(DataOrException(null,true,Exception("")))



    init {
        loadWeather()

    }

    private fun loadWeather() {
        getWeather("seattle")
    }

    private fun getWeather(city: String) {

        viewModelScope.launch {
            if (city.isEmpty()){
                return@launch
            }else{
                data.value.isLoading = true
                data.value = repository.getWeather(cityQuery = city)
            }

            if (data.value.data.toString().isNotEmpty()){
                data.value.isLoading = false
            }
            Log.d("TAG","getWeather: ${data.value.data.toString()}")


        }



    }

}
