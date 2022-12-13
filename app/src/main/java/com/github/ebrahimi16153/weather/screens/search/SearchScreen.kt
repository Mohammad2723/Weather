package com.github.ebrahimi16153.weather.screens.search

import android.annotation.SuppressLint

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import com.github.ebrahimi16153.weather.widgets.SearchContent
import com.github.ebrahimi16153.weather.widgets.WeatherAppBar

@ExperimentalComposeUiApi
@Composable
fun SearchScreen(navController: NavController) {

    SearchScaffold(navController = navController)


}

@ExperimentalComposeUiApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScaffold(navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Search",
            onSearchClicked = {},
            icon = Icons.Default.ArrowBack,
            onNavigationClicked = { navController.popBackStack() }
        )
    }) {

        SearchContent(navController = navController)
    }
}






