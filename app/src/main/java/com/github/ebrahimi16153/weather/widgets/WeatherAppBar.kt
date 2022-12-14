package com.github.ebrahimi16153.weather.widgets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.ebrahimi16153.weather.model.Favorites
import com.github.ebrahimi16153.weather.navigation.WeatherScreensName
import com.github.ebrahimi16153.weather.ui.theme.MyColors
import com.github.ebrahimi16153.weather.viewmodel.FavoriteViewModel

//val showDialog = mutableStateOf(false)

@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    onSearchClicked: () -> Unit = {},
    navController:NavController,
    onNavigationClicked: () -> Unit = { navController.popBackStack()}
) {

    val showDialog = remember {
        mutableStateOf(false)
    }

    if (showDialog.value) {
        ShowSettingDropDownMenu(showDialog = showDialog , navController = navController)
    }


    TopAppBar(
        title = { Text(text = title, color = MyColors().onPrimary.value) },
        actions = {
            //search button
            if (isMainScreen) {
                IconButton(onClick = { onSearchClicked.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search icon",
                        tint = MyColors().onPrimary.value
                    )

                }
                //more button
                IconButton(onClick = { showDialog.value = true }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "menu icon",
                        tint = MyColors().onPrimary.value
                    )

                }
            } else {
                Box {}
            }

        },
        //navigation button
        navigationIcon = {
            if (icon != null) {
                IconButton(onClick = { onNavigationClicked.invoke() }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "null",
                        tint = MyColors().onPrimary.value
                    )
                }
                //favorite Button
            } else if (isMainScreen) {


                // check the city already is favorite or not
                val isAlreadyFavList =
                    favoriteViewModel.favList.collectAsState().value.filter { item ->
                        (item.city == title.split(",")[0])
                    }

                //context -> it cant define in @Composable function

                // below code is define in onClick of IconButton and
                // oClick function isn't @Composable function
                val context = LocalContext.current

                IconButton(onClick = {
                    val dataList = title.split(",")
                    if (isAlreadyFavList.isEmpty()) {
                        // insert city from title to favorite list
                        favoriteViewModel.insertFavorite(

                            Favorites(
                                city = dataList[0], //city
                                country = dataList[1]
                            )   // country
                        )
                        // toast massage for added city to favorites list
                        Toast.makeText(
                            context,
                            "${dataList[0]} Added to Favorites",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        favoriteViewModel.deleteFavorite(
                            Favorites(
                                city = dataList[0], //city
                                country = dataList[1]
                            )
                        )
                    }


                }) {
                    Icon(
                        imageVector = if (isAlreadyFavList.isEmpty()) {
                            Icons.Rounded.FavoriteBorder
                        } else {
                            Icons.Rounded.Favorite
                        },
                        contentDescription = "fav icon",
                        tint = if (isAlreadyFavList.isEmpty()) {
                            MyColors().onPrimary.value
                        } else {
                            Color.Red
                        }
                    )
                }



            }
        },
        backgroundColor = MyColors().primary.value,
        elevation = elevation
    )

}

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean> , navController: NavController) {
    var isExpanded by remember { mutableStateOf(true) }
    val items = listOf("About", "Favorites", "Settings")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
                showDialog.value = false
            },
            modifier = Modifier
                .width(140.dp)
                .background(MyColors().background.value)
        ) {

            items.forEachIndexed { _, text ->
                DropdownMenuItem(onClick = {
                    isExpanded = false
                    showDialog.value = false
                }) {

                    Row(modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            isExpanded = false
                            showDialog.value = false
                            navController.navigate(
                                when (text) {
                                    "Favorites" -> WeatherScreensName.FavoritesScreen.name
                                    "Settings" -> WeatherScreensName.SettingsScreen.name
                                    else -> WeatherScreensName.AboutScreen.name
                                }
                            )

                        }) {
                        Icon(
                            imageVector = when (text) {
                                "About" -> Icons.Default.Info
                                "Favorites" -> Icons.Default.FavoriteBorder
                                else -> Icons.Default.Settings

                            }, contentDescription = null,
                            tint = MyColors().text.value
                        )
                        Text(text = " $text", fontWeight = FontWeight.Bold)

                    }
                }
            }
        }
    }
}


@Composable
fun ToastMessage(context: Context, showIt:Boolean){
    if (showIt){
        Toast.makeText(context , "Added To Favorites list",Toast.LENGTH_SHORT).show()
    }
}

