package com.github.ebrahimi16153.weather.util

import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color

class WeatherColor(
    val background: MutableState<Color>,
    val primary:  MutableState<Color>,
    val text:  MutableState<Color>,
    val onBackground: MutableState<Color>,
    val onPrimary:  MutableState<Color>
)