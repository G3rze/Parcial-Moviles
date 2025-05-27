package com.pdmcourse.spotlyfe.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory.newLatLng
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun SelecLocationMap(
    initialPosition: LatLng = LatLng(13.679024407659101,-89.2357878993471),
    onLocationChanged: (LatLng) -> Unit)
{
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialPosition, 16f)
    }

    val uiSettings by remember{
        mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
    }

    val markerState = rememberMarkerState(position = initialPosition)

    Box(Modifier.fillMaxWidth().padding(top = 16.dp).height(30.dp)) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = uiSettings,
            onMapClick = { newLatLng ->
                markerState.position = newLatLng
                onLocationChanged(newLatLng)
                Log.d("Map", "Selected location: $newLatLng")
            }
        ) {
            Marker(
                state = markerState,
            )
        }
    }
}