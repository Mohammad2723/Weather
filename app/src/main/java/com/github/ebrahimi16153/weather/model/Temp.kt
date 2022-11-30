package com.github.ebrahimi16153.weather.model


import com.google.gson.annotations.SerializedName

data class Temp(
    @SerializedName("day")
    val day: Double?, // 275.4
    @SerializedName("eve")
    val eve: Double?, // 274.71
    @SerializedName("max")
    val max: Double?, // 275.44
    @SerializedName("min")
    val min: Double?, // 273.41
    @SerializedName("morn")
    val morn: Double?, // 273.56
    @SerializedName("night")
    val night: Double? // 274.88
)