package com.pdmcourse.spotlyfe.data

import android.content.Context
import com.pdmcourse.spotlyfe.data.database.AppDatabase
import com.pdmcourse.spotlyfe.data.database.repository.PlaceRepository
import com.pdmcourse.spotlyfe.data.database.repository.PlaceRepostioryImp

class AppProvider(context: Context) {
  private val appDatabase = AppDatabase.getDatabase(context)

  private val placeDAO = appDatabase.placeDAO()

  private val placeRepository: PlaceRepository = PlaceRepostioryImp(placeDAO)

  fun providePlaceRepository() : PlaceRepository {
    return placeRepository
  }
}