package com.github.ebrahimi16153.weather.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.github.ebrahimi16153.weather.util.WeatherColor


@SuppressLint("ComposableNaming")
@Composable
fun MyColors(dark: Boolean = isSystemInDarkTheme()): WeatherColor {
    return if (dark) {
        WeatherColor(
            background = remember {
                mutableStateOf(darkBackground)
            },
            primary = remember {
                mutableStateOf(darkPrimary)
            },
            text = remember {
                mutableStateOf(darkText)
            },
            onBackground = remember {
                mutableStateOf(darkOnBackground)
            },
            onPrimary = remember {
                mutableStateOf(darkOnPrimary)
            }
        )
    } else {
        WeatherColor(
            background = remember {
                mutableStateOf(lightBackground)
            },
            primary = remember {
                mutableStateOf(lightPrimary)
            },
            text = remember {
                mutableStateOf(lightText)
            },
            onBackground = remember {
                mutableStateOf(lightOnBackground)
            },
            onPrimary = remember {
                mutableStateOf(lightOnPrimary)
            }

        )
    }
}