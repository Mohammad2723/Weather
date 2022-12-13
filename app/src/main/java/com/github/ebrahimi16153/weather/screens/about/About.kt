package com.github.ebrahimi16153.weather.screens.about

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.github.ebrahimi16153.weather.navigation.WeatherScreensName
import com.github.ebrahimi16153.weather.ui.theme.MyColors
import com.github.ebrahimi16153.weather.widgets.WeatherAppBar

@Composable
fun About(navController: NavHostController) {
    AboutScaffold(navController = navController)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AboutScaffold(navController: NavHostController) {

    Scaffold(topBar = {
        WeatherAppBar(
            onSearchClicked = { navController.navigate(WeatherScreensName.SearchScreen.name) },
            title = "About",
            icon = Icons.Default.ArrowBack,
            navController = navController
        )
    }) {
        MainContent()
    }

}



@Preview
@Composable
fun MainContent(){
    Surface(modifier = Modifier.fillMaxSize(), color = MyColors().background.value) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Developed By Mohammad Ebrahimi")
            Text(text = "github.com/ebrahimi16153")
        }

    }
}