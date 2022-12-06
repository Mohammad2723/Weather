package com.github.ebrahimi16153.weather.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.github.ebrahimi16153.weather.util.WeatherColor


@SuppressLint("ComposableNaming")
@Composable
fun MyColors(dark: Boolean = isSystemInDarkTheme()): WeatherColor {
    return if (dark) {
        WeatherColor(
            background = darkBackground,
            primary = darkPrimary,
            text = darkText,
            onBackground = darkOnBackground,
            onPrimary = darkOnPrimary
        )
    } else {
        WeatherColor(
            background = lightBackground,
            primary = lightPrimary,
            text = lightText,
            onBackground = lightOnBackground,
            lightOnPrimary

        )
    }
}