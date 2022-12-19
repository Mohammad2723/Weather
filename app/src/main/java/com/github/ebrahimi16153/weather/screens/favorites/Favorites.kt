package com.github.ebrahimi16153.weather.screens.favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.github.ebrahimi16153.weather.model.Favorites
import com.github.ebrahimi16153.weather.navigation.WeatherScreensName
import com.github.ebrahimi16153.weather.ui.theme.MyColors
import com.github.ebrahimi16153.weather.viewmodel.FavoriteViewModel
import com.github.ebrahimi16153.weather.widgets.EmptyList
import com.github.ebrahimi16153.weather.widgets.RowOfFavorite
import com.github.ebrahimi16153.weather.widgets.WeatherAppBar

@Composable
fun FavoritesScreen(
    navController: NavHostController,
    favoritesViewModel: FavoriteViewModel = hiltViewModel()
) {
    FavoritesScaffold(navController = navController, favoritesViewModel)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavoritesScaffold(navController: NavHostController, favoriteViewModel: FavoriteViewModel) {

    Scaffold(topBar = {
        WeatherAppBar(
            onSearchClicked = {},
            title = "Favorites",
            isMainScreen = false,
            icon = Icons.Default.ArrowBack,
            navController = navController
        )
    }) {
        MainContent(navController = navController, favoritesViewModel = favoriteViewModel)
    }

}


//@Preview
@Composable
fun MainContent(navController: NavController, favoritesViewModel: FavoriteViewModel) {

    /****** if you want update list after delete a item must use from below code *****/
    val list = favoritesViewModel.favList.collectAsState().value

    Surface(modifier = Modifier.fillMaxSize(), color = MyColors().background.value) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {


            if (list.isNotEmpty()) {

                LazyColumn(modifier = Modifier.padding(top = 10.dp, start = 8.dp, end = 8.dp)) {
                    items(items = list) { item ->

                        RowOfFavorite(
                            item = item,
                            navController = navController,
                            favoritesViewModel = favoritesViewModel
                        )

                    }
                }
            } else {
             EmptyList()
            }

        }

    }
}
