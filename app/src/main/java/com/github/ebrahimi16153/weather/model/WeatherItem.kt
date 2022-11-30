package com.github.ebrahimi16153.weather.model


import com.google.gson.annotations.SerializedName

data class WeatherItem(
    @SerializedName("clouds")
    val clouds: Int?, // 100
    @SerializedName("deg")
    val deg: Int?, // 355
    @SerializedName("dt")
    val dt: Int?, // 1669806000
    @SerializedName("feels_like")
    val feelsLike: FeelsLike?,
    @SerializedName("gust")
    val gust: Double?, // 5.89
    @SerializedName("humidity")
    val humidity: Int?, // 82
    @SerializedName("pop")
    val pop: Double?, // 0.29
    @SerializedName("pressure")
    val pressure: Int?, // 1018
    @SerializedName("rain")
    val rain: Double?, // 0.23
    @SerializedName("speed")
    val speed: Double?, // 2.37
    @SerializedName("sunrise")
    val sunrise: Int?, // 1669789838
    @SerializedName("sunset")
    val sunset: Int?, // 1669822743
    @SerializedName("temp")
    val temp: Temp?,
    @SerializedName("weather")
    val weather: List<WeatherObject>?
)