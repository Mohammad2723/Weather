package com.github.ebrahimi16153.weather.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.ebrahimi16153.weather.R
import com.github.ebrahimi16153.weather.navigation.WeatherScreensName
import com.github.ebrahimi16153.weather.ui.theme.MyColors
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    // animation
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true, block = {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(durationMillis = 1000, delayMillis = 800, easing = {
                OvershootInterpolator(8f).getInterpolation(it)
            })
        )

        delay(800)
        navController.popBackStack()
        navController.navigate(WeatherScreensName.MainScreen.name+"/London")

    })
   // end animation

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    )
    {
        Surface(
            modifier = Modifier
                .padding(15.dp)
                .size(330.dp)
                .scale(scale.value),
            color = Color.Transparent,
            shape = CircleShape,
            border = BorderStroke(width = 2.dp, color = MyColors().text.value)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.sun),
                    contentDescription = "",
                    tint = MyColors().text.value
                )
                Text(text = "Find the Sun?", color = MyColors().text.value)
            }


        }
    }


}