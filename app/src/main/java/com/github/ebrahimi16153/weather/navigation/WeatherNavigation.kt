package com.github.ebrahimi16153.weather.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.ebrahimi16153.weather.screens.SplashScreen
import com.github.ebrahimi16153.weather.screens.main.MainScreen
import com.github.ebrahimi16153.weather.screens.search.SearchScreen
import com.github.ebrahimi16153.weather.viewmodel.MainViewModel
@ExperimentalComposeUiApi
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

        // www.test.com/cityname = "Seattle"

        val route = WeatherScreensName.MainScreen.name
        composable("$route/{city}", arguments = listOf(
            navArgument(name = "city"){
                type = NavType.StringType
            }
        )) { navBack ->

            navBack.arguments?.getString("city").let {

                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController = navController , mainViewModel , city =it)
            }

        }

        composable(WeatherScreensName.SearchScreen.name){
            SearchScreen(navController = navController)
        }


    }
}