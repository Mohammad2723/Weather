package com.github.ebrahimi16153.weather.screens.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.github.ebrahimi16153.weather.ui.theme.MyColors
import com.github.ebrahimi16153.weather.widgets.WeatherAppBar

@Composable
fun SettingsScreen(navController: NavHostController) {
    SettingsScaffold(navController = navController)
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScaffold(navController: NavHostController) {

    Scaffold(topBar = {
        WeatherAppBar(
            title = "Settings",
            icon = Icons.Default.ArrowBack,
            isMainScreen = false,
            navController = navController,
        )
    }) {
        MainContent()
    }

}

@Preview
@Composable
fun MainContent() {
    var unitToggleState by remember {
        mutableStateOf(false)
    }
    val measurementUnit = listOf("Imperial (F)", "Metric (C)")
    var choiceState by remember {
        mutableStateOf("")
    }

    Surface(modifier = Modifier.fillMaxSize(), color = MyColors().background.value) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Change Units of Measurement", modifier = Modifier.padding(bottom = 15.dp))
            IconToggleButton(
                checked = !unitToggleState,
                onCheckedChange = {
                    unitToggleState = !it
                    choiceState = if (unitToggleState) {
                        "Imperial (F)"
                    } else {
                        "Metric (C)"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .clip(shape = CircleShape.copy(CornerSize(15.dp)))
                    .background(MyColors().background.value)
                    .padding(5.dp)
                    .border(width = 1.dp, color = MyColors().text.value)) {
                Text(
                    text = if (unitToggleState) {
                        "Fahrenheit °F"
                    }else{
                        "Celsius °C"
                    }
                )
            }
        }
    }
}