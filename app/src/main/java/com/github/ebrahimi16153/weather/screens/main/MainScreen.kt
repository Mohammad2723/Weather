package com.github.ebrahimi16153.weather.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.github.ebrahimi16153.weather.data.DataOrException
import com.github.ebrahimi16153.weather.model.Weather
import com.github.ebrahimi16153.weather.navigation.WeatherScreensName
import com.github.ebrahimi16153.weather.ui.theme.MyColors
import com.github.ebrahimi16153.weather.util.formatDate
import com.github.ebrahimi16153.weather.viewmodel.MainViewModel
import com.github.ebrahimi16153.weather.viewmodel.SettingsViewModel
import com.github.ebrahimi16153.weather.widgets.*


@ExperimentalComposeUiApi
@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    city: String?
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        val unitFromDB = settingsViewModel.unitList.collectAsState().value
//        val unitDef by remember {
//            mutableStateOf("metric")
//        }
        var isMetric by remember {
            mutableStateOf(false)
        }
        if (unitFromDB.isNotEmpty()){
           val  unit = unitFromDB[0].unit
            isMetric = unit == "metric"
            val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
                initialValue = DataOrException(isLoading = true)
            ) {
                value = viewModel.getWeatherData(city = city?:"London",unit = unit)
            }.value

            if (weatherData.isLoading == true) {
                CircularProgressIndicator(color = MyColors().text.value)

            } else if (weatherData.data != null) {

                MainScaffold(weather = weatherData.data!!, navController = navController , isMetric = isMetric)
//            HumidityWindPressureRow(weather = weatherData.data!!)
            } else {
                Text(text = "We can't find the city")
                SearchContent(navController = navController)

            }
        }

        Log.e("unit", settingsViewModel.unitList.collectAsState().value.size.toString())




    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScaffold(weather: Weather, navController: NavController, isMetric: Boolean) {

    // appBar
    Scaffold(topBar = {
        WeatherAppBar(
            title = "${weather.city?.name},${weather.city?.country}",
            onSearchClicked = { navController.navigate(WeatherScreensName.SearchScreen.name)},
            elevation = 5.dp,
            navController = navController
        ) {
//            Log.d("ArrowBack", "Arrow Back clicked")
        }
    }) {


        // mainContent
        MainContent(weather = weather,isMetric = isMetric)
    }


}

@Composable
fun MainContent(weather: Weather, isMetric: Boolean) {
    // icon Url
    val iconUrl =
        "https://openweathermap.org/img/wn/${weather.list?.get(0)?.weather?.get(0)?.icon}.png"

    // Main Column
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MyColors().background.value)
            .padding(all = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Top Date
        Text(
            text = "${weather.list?.get(0)?.dt?.let { formatDate(it) }}",
            color = MyColors().text.value,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        //Start Circle content
        Surface(
            modifier = Modifier
                .size(230.dp),
            shape = CircleShape, color = MyColors().primary.value
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // icon of clouds
                WeatherIcon(url = iconUrl)
               
                Log.d("Url",iconUrl)

                // Temp
                Text(
                    text = weather.list?.get(0)?.temp?.day?.toInt().toString() + "°",
                    color = MyColors().onPrimary.value,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 45.sp
                )

                // Clouds
                weather.list?.get(0)?.weather?.get(0)?.description?.let {
                    Text(
                        text = it,
                        color = MyColors().onPrimary.value,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        fontStyle = FontStyle.Italic
                    )
                }


            }
        }
        //end circle Content

        HumidityWindPressureRow(weather = weather ,isMetric =isMetric)
        DivideLine()
        SunRiseSunSet(weather = weather)
        DivideLine()
        ThisWeek(weather = weather)
    }


}



