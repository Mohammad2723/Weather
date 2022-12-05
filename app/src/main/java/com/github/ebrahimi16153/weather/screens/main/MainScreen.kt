package com.github.ebrahimi16153.weather.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.ebrahimi16153.weather.data.DataOrException
import com.github.ebrahimi16153.weather.model.Weather
import com.github.ebrahimi16153.weather.ui.theme.MyColors
import com.github.ebrahimi16153.weather.viewmodel.MainViewModel


@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ShowData(viewModel = viewModel)

    }

}


@Composable
fun ShowData(viewModel: MainViewModel) {
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(isLoading = true)
    ) {
        value = viewModel.getWeatherData(city = "karaj")
    }.value
    if (weatherData.isLoading == true) {
        CircularProgressIndicator(color = MyColors().text)

    } else if (weatherData.data != null) {

        Text(
            text = "MAIN SCREEN ${weatherData.data!!.city!!.country.toString()} ",
            color = MyColors().text
        )


        Text(
            text = "MAIN SCREEN ${weatherData.data!!.list?.get(0)?.temp} ",
            color = MyColors().text
        )

    }
}