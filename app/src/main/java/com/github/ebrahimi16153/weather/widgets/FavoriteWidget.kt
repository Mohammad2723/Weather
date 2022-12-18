package com.github.ebrahimi16153.weather.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.ebrahimi16153.weather.model.Favorites
import com.github.ebrahimi16153.weather.navigation.WeatherScreensName
import com.github.ebrahimi16153.weather.ui.theme.MyColors
import com.github.ebrahimi16153.weather.viewmodel.FavoriteViewModel


@Composable
fun RowOfFavorite(
    item: Favorites,
    navController: NavController,
    favoritesViewModel: FavoriteViewModel

) {

    Row(
        modifier = Modifier
            .clickable {

                navController.popBackStack()
                navController.navigate(WeatherScreensName.MainScreen.name + "/${item.city}")

            }
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item.city, color = MyColors().text.value, textAlign = TextAlign.Center)
        Text(text = item.country, color = MyColors().text.value, textAlign = TextAlign.Center)
        IconButton(onClick = {
            // delete favorite
            favoritesViewModel.deleteFavorite(item)
        }) {

            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "delete icon",
                tint = Color.Red.copy(alpha = 0.5f)
            )

        }
    }
}


@Composable
fun EmptyList() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(text = "Favorite list is empty", color = MyColors().text.value)

    }


}