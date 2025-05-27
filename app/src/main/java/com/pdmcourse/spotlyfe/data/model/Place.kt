package com.pdmcourse.spotlyfe.data.model

import com.pdmcourse.spotlyfe.data.database.entities.PlaceEntity

data class Place(
  val name: String,
  val remark: String,
  val latitude: Double,
  val longitude: Double,
)

fun Place.toEntity(): PlaceEntity{
  return PlaceEntity(
    name = name,
    remark = remark,
    latitude = latitude,
    longitude = longitude
  )
}