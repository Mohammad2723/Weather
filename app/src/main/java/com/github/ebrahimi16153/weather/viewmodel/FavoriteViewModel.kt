package com.github.ebrahimi16153.weather.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ebrahimi16153.weather.model.City
import com.github.ebrahimi16153.weather.model.Favorites
import com.github.ebrahimi16153.weather.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val weatherDbRepository: WeatherDbRepository) :
    ViewModel() {

    private val _favList = MutableStateFlow<List<Favorites>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {

            weatherDbRepository.getFavorites().distinctUntilChanged().collect() { listOfFavs ->
                if (listOfFavs.isEmpty()) {
                    Log.e("TAG", "EMPTY FAV")
                } else {
                    _favList.value = listOfFavs
                    Log.e("FAV", "${favList.value}")
                }
            }
        }
    }

    fun insertFavorite(favorites: Favorites) =
        viewModelScope.launch { weatherDbRepository.insertFavorite(favorites) }
    fun updateFavorite(favorites: Favorites) =
        viewModelScope.launch { weatherDbRepository.updateFavorite(favorites) }
    fun deleteAllFavorites() =
        viewModelScope.launch { weatherDbRepository.deleteAllFavorite() }
    fun deleteFavorite(favorites: Favorites) =
        viewModelScope.launch { weatherDbRepository.deleteFavorite(favorites) }



}