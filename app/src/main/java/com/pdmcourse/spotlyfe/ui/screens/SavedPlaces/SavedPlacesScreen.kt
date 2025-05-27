package com.pdmcourse.spotlyfe.ui.screens.SavedPlaces

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.pdmcourse.spotlyfe.data.model.Place
import com.pdmcourse.spotlyfe.ui.layout.CustomFloatingButton
import com.pdmcourse.spotlyfe.ui.layout.CustomTopBar
import com.pdmcourse.spotlyfe.ui.navigation.AddPlaceScreenNavigation

@Composable
fun SavedPlacesScreen(
  savedPlacesViewModel: SavedPlacesViewModel = viewModel(factory = SavedPlacesViewModel.Factory)
) {

  val savedPlaces = savedPlacesViewModel.savedPlaces.collectAsState(emptyList())
  val navController = rememberNavController()

  val cameraPositionState = rememberCameraPositionState {
    position = CameraPosition.fromLatLngZoom(LatLng(savedPlaces.value.get(0).latitude, savedPlaces.value.get(0).longitude), 16f)
  }

  var uiSettings by remember {
    mutableStateOf(MapUiSettings(zoomControlsEnabled = false))
  }
  var properties by remember {
    mutableStateOf(MapProperties(mapType = MapType.HYBRID))
  }

  Scaffold(
    topBar = { CustomTopBar() },
    floatingActionButton = { CustomFloatingButton(onClick = {
      navController.navigate(AddPlaceScreenNavigation)
    })}
  ) { innerPadding ->
    Column(modifier = Modifier.padding(innerPadding)) {

      GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = properties,
        uiSettings = uiSettings
      ) {
        for (place in savedPlaces.value) {
          Marker(
            state = MarkerState(position = LatLng(place.latitude, place.longitude)),
            title = place.name,
            snippet = place.remark
          )
        }
      }
    }
  }
}