package com.pdmcourse.spotlyfe.ui.screens.SavedPlaces

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.pdmcourse.spotlyfe.SpotLyfeApplication
import com.pdmcourse.spotlyfe.data.database.repository.PlaceRepository
import com.pdmcourse.spotlyfe.data.model.Place
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SavedPlacesViewModel(
    private val placeRepository: PlaceRepository
): ViewModel() {

    val savedPlaces: Flow<List<Place>> = placeRepository.getAllPlaces()




    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SpotLyfeApplication)
                SavedPlacesViewModel(application.appProvider.providePlaceRepository())
            }
        }
    }
}