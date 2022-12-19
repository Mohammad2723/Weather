package com.github.ebrahimi16153.weather

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.github.ebrahimi16153.weather.navigation.WeatherNavigation
import com.github.ebrahimi16153.weather.ui.theme.MyColors
import com.github.ebrahimi16153.weather.ui.theme.WeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApp()
        }
    }


    override fun onResume() {
        super.onResume()
        Log.d("onResume","onResume")
    }

}
@ExperimentalComposeUiApi
@Composable
fun WeatherApp() {
    WeatherTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MyColors().background.value) {
            WeatherNavigation()
        }

    }
}



