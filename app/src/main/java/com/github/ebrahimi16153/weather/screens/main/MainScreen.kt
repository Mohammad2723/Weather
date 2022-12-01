package com.github.ebrahimi16153.weather.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.ebrahimi16153.weather.ui.theme.MyColors
import com.github.ebrahimi16153.weather.viewmodel.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "MAIN SCREEN " , color = MyColors().text)

    }
}