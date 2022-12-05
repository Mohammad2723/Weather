package com.github.ebrahimi16153.weather.widgets

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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


    TopAppBar(backgroundColor = MyColors().background) {
        Text(text = title, color = MyColors().text,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp))

    }

}