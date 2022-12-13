package com.github.ebrahimi16153.weather.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.ebrahimi16153.weather.ui.theme.MyColors

//val showDialog = mutableStateOf(false)

@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    onSearchClicked: () -> Unit,
    onNavigationClicked: () -> Unit = {}
) {

    val showDialog = remember {
        mutableStateOf(false)
    }

    if (showDialog.value) {
        ShowSettingDropDownMenu(showDialog = showDialog)
    }


    TopAppBar(
        title = { Text(text = title, color = MyColors().onPrimary.value) },
        actions = {
            //search button
            if (isMainScreen) {
                IconButton(onClick = { onSearchClicked.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search icon",
                        tint = MyColors().onPrimary.value
                    )

                }
                //more button
                IconButton(onClick = { showDialog.value = true }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "menu icon",
                        tint = MyColors().onPrimary.value
                    )

                }
            } else {
                Box {}
            }

        },
        navigationIcon = {
            if (icon != null) {
                IconButton(onClick = { onNavigationClicked.invoke() }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "null",
                        tint = MyColors().onPrimary.value
                    )
                }
            }
        },
        backgroundColor = MyColors().primary.value,
        elevation = elevation
    )

}

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>) {
    var isExpanded by remember { mutableStateOf(true) }
    val items = listOf("About", "Favorites", "Settings")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
                showDialog.value = false
            },
            modifier = Modifier
                .width(140.dp)
                .background(MyColors().background.value)
        ) {

            items.forEachIndexed { _, text ->
                DropdownMenuItem(onClick = {
                    isExpanded = false
                    showDialog.value = false
                }) {

                    Row(modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            isExpanded = false
                            showDialog.value = false
                        }) {
                        Icon(
                            imageVector = when (text) {
                                "About" -> Icons.Default.Info
                                "Favorites" -> Icons.Default.FavoriteBorder
                                else -> Icons.Default.Settings

                            }, contentDescription = null,
                            tint = MyColors().text.value
                        )
                        Text(text = " $text", fontWeight = FontWeight.Bold)

                    }

                }
            }


        }

    }


}

