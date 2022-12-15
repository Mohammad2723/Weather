package com.github.ebrahimi16153.weather.repository

import com.github.ebrahimi16153.weather.data.WeatherDao
import com.github.ebrahimi16153.weather.model.Favorites
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor( private val weatherDao: WeatherDao) {


     fun getFavorites(): Flow<List<Favorites>> = weatherDao.getFavorites()


    suspend fun insertFavorite(favorites: Favorites) = weatherDao.insertFavorite(favorites)
    suspend fun updateFavorite(favorites: Favorites) = weatherDao.updateFavorite(favorites)
    suspend fun deleteAllFavorite() = weatherDao.deleteAllFavorite()
    suspend fun deleteFavorite(favorites: Favorites) = weatherDao.deleteFavorite(favorites)

}