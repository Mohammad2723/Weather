package com.github.ebrahimi16153.weather.data

import androidx.room.*
import com.github.ebrahimi16153.weather.model.Favorites
import com.github.ebrahimi16153.weather.model.UnitWeather
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDao {

    @Query("SELECT * FROM fav_tbl")
    fun getFavorites():Flow<List<Favorites>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorites: Favorites)

    @Update
    suspend fun updateFavorite(favorites: Favorites)


    @Query("DELETE FROM fav_tbl")
    suspend fun deleteAllFavorite()


    @Delete
    suspend fun deleteFavorite(favorites: Favorites)


    ///////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////
    //Unit Weather
    @Query("SELECT * FROM unit_tbl")
    fun getUnit(): Flow<List<UnitWeather>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unitWeather: UnitWeather)

    @Update
    suspend fun updateUnit(unitWeather: UnitWeather)


    @Query("DELETE FROM unit_tbl")
    suspend fun deleteAllUnit()


    @Delete
    suspend fun deleteUnit(unitWeather: UnitWeather)

}