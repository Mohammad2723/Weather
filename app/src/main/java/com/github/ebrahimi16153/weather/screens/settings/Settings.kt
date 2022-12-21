package com.github.ebrahimi16153.weather.screens.settings

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.ebrahimi16153.weather.model.UnitWeather
import com.github.ebrahimi16153.weather.ui.theme.MyColors
import com.github.ebrahimi16153.weather.viewmodel.SettingsViewModel
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
    val settingsViewModel: SettingsViewModel = hiltViewModel()
    var unitToggleState by remember {
        mutableStateOf(false)
    }
    val measurementUnit = listOf("Imperial (F)", "Metric (C)")
    val choiceFromDb = settingsViewModel.unitList.collectAsState().value
    val defaultChoice = if (choiceFromDb.isEmpty()) measurementUnit[1]else choiceFromDb[0].unit
    var choiceState by remember {
        mutableStateOf(defaultChoice)
    }

 Log.e("defult" , choiceState)


    Surface(modifier = Modifier.fillMaxSize(), color = MyColors().background.value) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Change Units of Measurement", modifier = Modifier.padding(bottom = 15.dp))


            // toggle for select unit
            IconToggleButton(
                checked = !unitToggleState,
                onCheckedChange = {
                    unitToggleState = !it
                    choiceState = if (unitToggleState) {
                        "imperial"
                    } else {
                        "metric"
                    }

                    settingsViewModel.deleteAllUnits()
                    settingsViewModel.insertUnit(UnitWeather(unit = choiceState))
                },
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .background(MyColors().background.value)
                    .padding(5.dp)
                    .border(width = 1.dp, color = MyColors().text.value)
                    .clip(shape = CircleShape.copy(CornerSize(15.dp)))
            ) {
                Text(
                    text = if (unitToggleState) {
                        "Fahrenheit °F"
                    } else {
                        "Celsius °C"
                    }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    settingsViewModel.deleteAllUnits()
                    settingsViewModel.insertUnit(UnitWeather(unit = choiceState))

                },
                colors = ButtonDefaults.buttonColors(backgroundColor = MyColors().background.value),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(width = 1.dp, color = MyColors().text.value)
            ) {
                Text(text = "Save", color = MyColors().text.value)
            }


        }
    }
}