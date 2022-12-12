package com.github.ebrahimi16153.weather.widgets

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import com.github.ebrahimi16153.weather.navigation.WeatherScreensName
import com.github.ebrahimi16153.weather.ui.theme.MyColors

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


            SearchBar { city ->
                Log.d("SearchBar", city)
                navController.popBackStack()
                navController.navigate(WeatherScreensName.MainScreen.name+"/$city")
            }

        }
    }

}


@ExperimentalComposeUiApi
@Composable
fun SearchBar( onSearch: (String) -> Unit = {}) {
    // sre
    val searchQueryState = rememberSaveable {
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty()

    }

    CommonTextField(
        valueState = searchQueryState,
        placeHolder = "Where do you live?",
        onAction = KeyboardActions {
            if (!valid) return@KeyboardActions
            onSearch(searchQueryState.value.trim())
            searchQueryState.value = ""
            keyboardController?.hide()

        })


    Spacer(modifier = Modifier.height(15.dp))

    Button(colors = ButtonDefaults.buttonColors(backgroundColor = MyColors().primary.value),
        onClick = {
            if (!valid) return@Button
            onSearch(searchQueryState.value.trim())
            searchQueryState.value = ""
            keyboardController?.hide()
        }) {

        Text(text = "Change City", color = MyColors().onPrimary.value)

    }

}
