package com.pdmcourse.spotlyfe.ui.screens.AddPlace

import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdmcourse.spotlyfe.SpotLyfeApplication
import com.pdmcourse.spotlyfe.data.database.repository.PlaceRepository
import com.pdmcourse.spotlyfe.data.model.Place
import com.pdmcourse.spotlyfe.ui.screens.SavedPlaces.SavedPlacesViewModel

class AddPlaceViewModel(
    private val placeRepository: PlaceRepository
) : ViewModel() {


    var name = mutableStateOf("")

    var remark = mutableStateOf("")

    suspend fun newPlace(name:String, remark:String, latitude: Double, longitude:Double){
        var place = Place(
            name = name,
            remark = remark,
            latitude = latitude,
            longitude = longitude,
        )
        placeRepository.addPlace(place)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SpotLyfeApplication)
                AddPlaceViewModel(application.appProvider.providePlaceRepository())
            }
        }
    }

}