package com.pdmcourse.spotlyfe.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pdmcourse.spotlyfe.data.database.entities.PlaceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDAO {

    @Insert
    suspend fun addPlace(place: PlaceEntity)

    @Update
    suspend fun updatePlace(place: PlaceEntity)

    @Delete
    suspend fun deletePlace(place: PlaceEntity)

    @Query("SELECT * from places where id = :id")
    fun getPlaceById(id: Int): Flow<PlaceEntity>

    @Query("SELECT * from places")
    fun getAllPlaces(): Flow<List<PlaceEntity>>
}