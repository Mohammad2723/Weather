package com.github.ebrahimi16153.weather.screens.searchscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.ebrahimi16153.weather.ui.theme.MyColors
import com.github.ebrahimi16153.weather.widgets.CommonTextField
import com.github.ebrahimi16153.weather.widgets.WeatherAppBar

@ExperimentalComposeUiApi
@Composable
fun SearchScreen(navController: NavController) {

    SearchScaffold(navController = navController)


}

@ExperimentalComposeUiApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScaffold(navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Search",
            navController = navController,
            onSearchClicked = {},
            icon = Icons.Default.ArrowBack,
            onNavigationClicked = { navController.popBackStack() }
        )
    }) {

        SearchContent(navController = navController)
    }
}

@ExperimentalComposeUiApi
@Composable
fun SearchContent(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(), color = MyColors().background.value) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            SearchBar() {

            }

        }
    }

}


@ExperimentalComposeUiApi
@Composable
fun SearchBar(onSearch: (String) -> Unit = {}) {
    val searchQueryState = rememberSaveable {
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState) {
        searchQueryState.value.trim().isNotBlank()
    }

    CommonTextField(
        valueState = searchQueryState,
        placeHolder = "Where do you live?",
        onAction = KeyboardActions { if (!valid) return@KeyboardActions
        onSearch(searchQueryState.value.trim())
         searchQueryState .value =""
        keyboardController?.hide()})

}
