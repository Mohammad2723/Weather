package com.github.ebrahimi16153.weather.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.github.ebrahimi16153.weather.R
import com.github.ebrahimi16153.weather.model.Weather
import com.github.ebrahimi16153.weather.model.WeatherItem
import com.github.ebrahimi16153.weather.ui.theme.MyColors
import com.github.ebrahimi16153.weather.util.formatDateTime
import com.github.ebrahimi16153.weather.util.formatDays

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
fun HumidityWindPressureRow(weather: Weather) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .background(MyColors().background.value),
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
            .background(MyColors().background.value),
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
        color = MyColors().text.value
    ) {}


}

@Composable
fun ThisWeek(weather: Weather) {

    LazyColumn(modifier = Modifier.padding(top = 10.dp)) {

        weather.list?.let {
            items(items = it) { weatherItem ->
                RowOfThisWeek(item = weatherItem)
            }
        }
    }
}


@Composable
fun RowOfThisWeek(item: WeatherItem) {
    val iconUrl = "https://openweathermap.org/img/wn/${item.weather?.get(0)?.icon}.png"
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .height(50.dp),
        shape = RoundedCornerShape(8.dp),
        color = MyColors().background.value
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // days of week
            Text(
                text = "${item.dt?.let { formatDays(it) }}",
                color = MyColors().text.value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            // icon of days
            Image(
                modifier = Modifier.size(40.dp),
                painter = rememberImagePainter(iconUrl),
                contentDescription = "icon weather"
            )

            // description of days
            Text(
                text = "${item.weather?.get(0)?.description}",
                color = MyColors().text.value,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            // max temp and min temp for days
            Row {
                Text(
                    text = "${item.temp?.max?.toInt()}" + "°",
                    color = MyColors().text.value,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "| ${item.temp?.min?.toInt()}" + "°",
                    color = MyColors().text.value,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }


    }
}