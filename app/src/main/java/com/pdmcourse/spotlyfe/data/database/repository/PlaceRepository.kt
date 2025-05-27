package com.pdmcourse.spotlyfe.data.database.repository

import com.pdmcourse.spotlyfe.data.model.Place
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {

    fun getAllPlaces(): Flow<List<Place>>

    fun getPlaceById(id: Int): Flow<Place>

    suspend fun addPlace(place: Place)

    suspend fun updatePlace(place: Place)

    suspend fun deletePlace(place: Place)

}