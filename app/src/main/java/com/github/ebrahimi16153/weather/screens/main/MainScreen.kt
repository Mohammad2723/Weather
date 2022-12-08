package com.github.ebrahimi16153.weather.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.github.ebrahimi16153.weather.R
import com.github.ebrahimi16153.weather.data.DataOrException
import com.github.ebrahimi16153.weather.model.Weather
import com.github.ebrahimi16153.weather.model.WeatherItem
import com.github.ebrahimi16153.weather.navigation.WeatherScreensName
import com.github.ebrahimi16153.weather.ui.theme.MyColors
import com.github.ebrahimi16153.weather.util.formatDate
import com.github.ebrahimi16153.weather.util.formatDateTime
import com.github.ebrahimi16153.weather.viewmodel.MainViewModel
import com.github.ebrahimi16153.weather.widgets.WeatherAppBar


@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
            initialValue = DataOrException(isLoading = true)
        ) {
            value = viewModel.getWeatherData(city = "hashtgerd")
        }.value

        if (weatherData.isLoading == true) {
            CircularProgressIndicator(color = MyColors().text)

        } else if (weatherData.data != null) {

            MainScaffold(weather = weatherData.data!!, navController = navController)
            HumidityWindPressureRow(weather = weatherData.data!!)
        } else {
            Text(text = "We can't find the city")
            TryAgain {
                navController.popBackStack()
                navController.navigate(WeatherScreensName.MainScreen.name)
            }
        }


    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScaffold(weather:Weather ,navController: NavController) {

    // appBar
    Scaffold(topBar = {
        WeatherAppBar(
            title = "${weather.city?.name},${weather.city?.country}",
            navController = navController,
            icon = Icons.Default.ArrowBack,
            elevation = 5.dp
        ) {
            Log.d("ArrowBack", "Arrow Back clicked")
        }
    }) {


        // mainContent
        MainContent(weather = weather)
    }


}

@Composable
fun MainContent(weather: Weather) {
    // icon Url
    val iconUrl =
        "https://openweathermap.org/img/wn/${weather.list?.get(0)?.weather?.get(0)?.icon}.png"

    // Main Column
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MyColors().background)
            .padding(all = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Top Date
        Text(
            text = "${weather.list?.get(0)?.dt?.let { formatDate(it) }}",
            color = MyColors().text,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        //Start Circle content
        Surface(
            modifier = Modifier
                .size(230.dp),
            shape = CircleShape, color = MyColors().primary
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
                    color = MyColors().onPrimary,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 35.sp
                )

                // Clouds
                weather.list?.get(0)?.weather?.get(0)?.description?.let {
                    Text(
                        text = it,
                        color = MyColors().onPrimary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        fontStyle = FontStyle.Italic
                    )
                }


            }
        }
        //end circle Content

        HumidityWindPressureRow(weather = weather)
        DivideLine()
        SunRiseSunSet(weather = weather)
        DivideLine()
        ThisWeek(weather = weather)
    }


}

@Composable
fun WeatherIcon(url: String) {

    Image(
        // coil rememberImagePainter
        painter = rememberImagePainter(url),
        contentDescription = "icon",
        modifier = Modifier.size(50.dp)
    )

}


@Composable
fun TryAgain(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "TryAgain")
    }

}

@Composable
fun HumidityWindPressureRow(weather: Weather) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .background(MyColors().background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // humidity
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "humidity",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = weather.list?.get(0)?.humidity.toString() + "%",
                style = MaterialTheme.typography.caption
            )
        }
        //pressure
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "pressure",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = weather.list?.get(0)?.pressure.toString() + " psi",
                style = MaterialTheme.typography.caption
            )
        }
        //wind
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "wind",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = weather.list?.get(0)?.speed.toString() + " k/h",
                style = MaterialTheme.typography.caption
            )
        }


    }

}

@Composable
fun SunRiseSunSet(weather: Weather) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .background(MyColors().background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // sunrise
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "sunrise",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weather.list?.get(0)?.sunrise?.let { formatDateTime(it) }}",
                style = MaterialTheme.typography.caption
            )
        }

        //sunset
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "sunset",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weather.list?.get(0)?.sunset?.let { formatDateTime(it) }}",
                style = MaterialTheme.typography.caption
            )
        }


    }

}

@Composable
fun DivideLine() {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp),
        color = MyColors().text
    ) {}


}

@Composable
fun ThisWeek(weather: Weather) {

    LazyColumn {

        weather.list?.let {
            items(items = it) { weatherItem ->
              RowOfThisWeek(item = weatherItem)
            }
        }

    }


}

@Composable
fun RowOfThisWeek(item: WeatherItem) {
    Surface(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
          Text(text = "${item.dt?.let { formatDate(it) }}")
    }
}

