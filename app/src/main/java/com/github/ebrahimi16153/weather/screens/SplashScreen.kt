package com.github.ebrahimi16153.weather.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.ebrahimi16153.weather.R
import com.github.ebrahimi16153.weather.ui.theme.MyColors

@Composable
fun SplashScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    )
    {
        Surface(
            modifier = Modifier
                .padding(15.dp)
                .size(330.dp),
            color = Color.Transparent,
            shape = CircleShape,
            border = BorderStroke(width = 2.dp, color = MyColors().text)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.sun),
                    contentDescription = "",
                    tint = MyColors().text
                )
                Text(text = "Find The Sun?", color = MyColors().text)
            }


        }
    }


}