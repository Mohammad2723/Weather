package com.github.ebrahimi16153.weather.model


import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("coord")
    val coord: Coord?,
    @SerializedName("country")
    val country: String?, // IT
    @SerializedName("id")
    val id: Int?, // 3163858
    @SerializedName("name")
    val name: String?, // Zocca
    @SerializedName("population")
    val population: Int?, // 4593
    @SerializedName("timezone")
    val timezone: Int? // 3600
)