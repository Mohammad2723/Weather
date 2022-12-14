package com.github.ebrahimi16153.weather.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.github.ebrahimi16153.weather.model.Favorites
import kotlinx.coroutines.flow.Flow
import retrofit2.http.DELETE


@Dao
interface WeatherDao {

    @Query("SELECT * FROM fav_tbl")
    fun getFavorites():Flow<List<Favorites>>

    @Query("SELECT * FROM fav_tbl WHERE city =:city")
    suspend fun getFavById(city: String):Favorites


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorites: Favorites)

    @Update
    suspend fun updateFavorite(favorites: Favorites)


    @Query("DELETE FROM fav_tbl")
    suspend fun deleteAllFavorite()


    @DELETE
    suspend fun deleteFavorite(favorites: Favorites)

}