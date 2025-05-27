package com.pdmcourse.spotlyfe.data.database.repository

import com.pdmcourse.spotlyfe.data.database.dao.PlaceDAO
import com.pdmcourse.spotlyfe.data.database.entities.toDomain
import com.pdmcourse.spotlyfe.data.model.Place
import com.pdmcourse.spotlyfe.data.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlaceRepostioryImp(
    private val placeDAO: PlaceDAO
) : PlaceRepository {
    override suspend fun addPlace(place: Place) {
        placeDAO.addPlace(place.toEntity())
    }

    override fun getAllPlaces(): Flow<List<Place>> {
        return placeDAO.getAllPlaces().map {
            list -> list.map {
                it.toDomain()
            }
        }
    }

    override fun getPlaceById(id: Int): Flow<Place> {
        return placeDAO.getPlaceById(id).map {
            it.toDomain()
        }
    }

    override suspend fun updatePlace(place: Place) {
        placeDAO.updatePlace(place.toEntity())
    }

    override suspend fun deletePlace(place: Place) {
        placeDAO.deletePlace(place.toEntity())
    }
}