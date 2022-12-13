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
import com.github.ebrahimi16153.weather.screens.about.About
import com.github.ebrahimi16153.weather.screens.favorites.FavoritesScreen
import com.github.ebrahimi16153.weather.screens.main.MainScreen
import com.github.ebrahimi16153.weather.screens.search.SearchScreen
import com.github.ebrahimi16153.weather.screens.settings.SettingsScreen
import com.github.ebrahimi16153.weather.viewmodel.MainViewModel

@ExperimentalComposeUiApi
@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WeatherScreensName.SplashScreen.name
    ) {

        //splash screen
        composable(WeatherScreensName.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        // Main Screen
        //www.google.com/city-name="seattle"

        val route = WeatherScreensName.MainScreen.name

        composable("$route/{city}", arguments = listOf(navArgument(name = "city") {
            type = NavType.StringType })) { navBack ->

            navBack.arguments?.getString("city").let { city ->

                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController = navController, mainViewModel, city = city)
            }


        }

        //search Screen
        composable(WeatherScreensName.SearchScreen.name) {
            SearchScreen(navController = navController)
        }

        //About
        composable(WeatherScreensName.AboutScreen.name){
            About(navController = navController)
        }

        //Favorites
        composable(WeatherScreensName.FavoritesScreen.name){
            FavoritesScreen(navController = navController)
        }
        
        
        //settings
        composable(WeatherScreensName.SettingsScreen.name){
            SettingsScreen(navController = navController)
        }


    }
}