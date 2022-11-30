package com.github.ebrahimi16153.weather.model


import com.google.gson.annotations.SerializedName

data class FeelsLike(
    @SerializedName("day")
    val day: Double?, // 272.92
    @SerializedName("eve")
    val eve: Double?, // 273.06
    @SerializedName("morn")
    val morn: Double?, // 273.56
    @SerializedName("night")
    val night: Double? // 272.89
)