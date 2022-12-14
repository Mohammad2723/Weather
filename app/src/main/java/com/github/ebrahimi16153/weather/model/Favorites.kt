package com.github.ebrahimi16153.weather.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



// data class for save favorites city in RoomDataBase

@Entity(tableName = "fav_tbl")
data class Favorites(
    @PrimaryKey
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "country")
    val country: String
)
