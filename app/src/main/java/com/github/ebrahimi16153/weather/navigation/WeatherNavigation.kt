package com.github.ebrahimi16153.weather.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.ebrahimi16153.weather.screens.SplashScreen
import com.github.ebrahimi16153.weather.screens.main.MainScreen
import com.github.ebrahimi16153.weather.viewmodel.MainViewModel

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WeatherScreensName.SplashScreen.name
    ) {

        composable(WeatherScreensName.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(WeatherScreensName.MainScreen.name) {
            val mainViewModel = hiltViewModel<MainViewModel>()
            MainScreen(navController = navController, viewModel = viewModel())
        }


    }
}