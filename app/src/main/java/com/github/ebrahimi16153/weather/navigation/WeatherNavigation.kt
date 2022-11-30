package com.github.ebrahimi16153.weather.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.ebrahimi16153.weather.screens.SplashScreen
import com.github.ebrahimi16153.weather.screens.main.MainScreen

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
            MainScreen(navController = navController)
        }


    }
}