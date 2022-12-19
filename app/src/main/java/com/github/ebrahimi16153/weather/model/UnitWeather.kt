package com.github.ebrahimi16153.weather.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "unit_tbl")
data class UnitWeather(
    @PrimaryKey
    @ColumnInfo(name = "unit")
    val unit:String
)