package com.pdmcourse.spotlyfe.ui.screens.AddPlace

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.pdmcourse.spotlyfe.data.model.Place
import com.pdmcourse.spotlyfe.ui.components.SelecLocationMap
import com.pdmcourse.spotlyfe.ui.layout.CustomFloatingButton
import com.pdmcourse.spotlyfe.ui.layout.CustomTopBar
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@Composable
    fun AddPlaceScreen(navController: NavHostController,viewModel: AddPlaceViewModel = viewModel(factory = AddPlaceViewModel.Factory)) {

        var name by remember { viewModel.name }
        var remark by remember { viewModel.remark }

        var marker = rememberMarkerState(position = LatLng(13.679024407659101,-89.2357878993471))

        val coroutineScope = rememberCoroutineScope()

        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(LatLng(marker.position.latitude,marker.position.longitude), 16f)
        }

        var uiSettings by remember {
            mutableStateOf(MapUiSettings(zoomControlsEnabled = false))
        }
        var properties by remember {
            mutableStateOf(MapProperties(mapType = MapType.HYBRID))
        }

        Scaffold(
            topBar = { CustomTopBar(
                onBackPressed = {
                    navController.popBackStack()
                }
            ) },
            floatingActionButton = { CustomFloatingButton(onClick = {})}
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {

                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Label") }
                )

                TextField(
                    value = remark,
                    onValueChange = { remark = it },
                    label = { Text("Label") }
                )

                SelecLocationMap(onLocationChanged = {
                    marker.position = it
                })

                Button(onClick = {
                    coroutineScope.launch {
                        viewModel.newPlace(name, remark, marker.position.latitude, marker.position.longitude)
                    }
                    navController.popBackStack()

                },
                    modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                    Text("Guardar")
                }
            }
        }


    }
