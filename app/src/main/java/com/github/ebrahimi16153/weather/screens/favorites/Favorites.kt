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
import com.github.ebrahimi16153.weather.widgets.WeatherAppBar

@Composable
fun FavoritesScreen(navController: NavHostController) {
    AboutScaffold(navController = navController)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AboutScaffold(navController: NavHostController) {

    Scaffold(topBar = {
        WeatherAppBar(
            onSearchClicked = { navController.navigate(WeatherScreensName.SearchScreen.name) },
            title = "Favorites",
            icon = Icons.Default.ArrowBack,
            navController = navController
        )
    }) {
        MainContent(navController = navController)
    }

}


//@Preview
@Composable
fun MainContent(navController: NavController) {
    val favoriteViewModel: FavoriteViewModel = hiltViewModel()
    Surface(modifier = Modifier.fillMaxSize(), color = MyColors().background.value) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            LazyColumn {
                items(items = favoriteViewModel.favList.value) { item ->
                    RowOfFavorite(
                        item = item,
                        onDeleteIconClick = { favorites ->
                            favoriteViewModel.deleteFavorite(favorites)
                        }) { city ->
                        navController.popBackStack()
                        navController.navigate(WeatherScreensName.MainScreen.name + "/$city")
                    }
                }
            }

        }

    }
}

@Composable
fun RowOfFavorite(
    item: Favorites,
    onDeleteIconClick: (Favorites) -> Unit,
    onRowClick: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .clickable { onRowClick(item.city) }
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item.city, color = MyColors().text.value, textAlign = TextAlign.Center)
        Text(text = item.country, color = MyColors().text.value, textAlign = TextAlign.Center)
        IconButton(onClick = {
            onDeleteIconClick(
                Favorites(
                    city = item.city,
                    country = item.country
                )
            )
        }) {

            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "delete icon",
                tint = Color.Red.copy(alpha = 0.5f)
            )

        }
    }


}
