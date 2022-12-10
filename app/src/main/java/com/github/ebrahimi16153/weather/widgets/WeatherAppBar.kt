package com.github.ebrahimi16153.weather.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.ebrahimi16153.weather.ui.theme.MyColors

@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddActionClicked: () -> Unit = {}
) {


    TopAppBar(
        title = { Text(text = title, color = MyColors().onPrimary.value) },
        actions = {
            //search button
            if (isMainScreen) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search icon",
                        tint = MyColors().onPrimary.value
                    )

                }
                //more button
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Search icon",
                        tint = MyColors().onPrimary.value
                    )

                }
            } else {
                Box {}
            }

        },
        navigationIcon = {
            if (icon != null) {
                IconButton( onClick = {onAddActionClicked.invoke()}) {
                 Icon(imageVector = icon, contentDescription = "null" , tint = MyColors().onPrimary.value)
                }
            }
        },
        backgroundColor = MyColors().primary.value,
        elevation = elevation
    )

}