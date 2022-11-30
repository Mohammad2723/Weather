package com.github.ebrahimi16153.weather.model


import com.google.gson.annotations.SerializedName

data class WeatherObject(
    @SerializedName("description")
    val description: String?, // overcast clouds
    @SerializedName("icon")
    val icon: String?, // 04d
    @SerializedName("id")
    val id: Int?, // 804
    @SerializedName("main")
    val main: String? // Clouds
)