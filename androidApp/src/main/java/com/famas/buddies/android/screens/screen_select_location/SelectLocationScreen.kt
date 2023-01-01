package com.famas.buddies.android.screens.screen_select_location

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.famas.buddies.android.components.AutoCompleteTextField
import com.famas.buddies.android.core.theme.SpaceLarge
import com.famas.buddies.android.core.theme.SpaceMedium
import com.famas.buddies.android.screens.destinations.AddBuddyScreenDestination
import com.famas.buddies.android.util.getScreenSize
import com.famas.buddies.feature_select_map.interactors.SelectLocationEvent
import com.famas.buddies.feature_select_map.interactors.SelectLocationVM
import com.google.maps.android.compose.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.DestinationStyle
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
@Destination(route = "Pick Location")
fun SelectLocationScreen(
    navController: NavController
) {
    val screenSize = getScreenSize()

    val viewModel: SelectLocationVM = getViewModel()
    val cameraPosition = rememberCameraPositionState()

    val state = viewModel.state.collectAsState().value

    val mapProperties = remember {
        MapProperties(isMyLocationEnabled = true)
    }

    LaunchedEffect(key1 = cameraPosition.position.target, block = {
        delay(1000)
        viewModel.onEvent(
            SelectLocationEvent.OnChangeLocation(
                cameraPosition.position.target.latitude,
                cameraPosition.position.target.longitude
            )
        )
    })

    LaunchedEffect(key1 = state.places, block = {
        Log.d("myTag", "${state.places.size}")
    })

    Box(modifier = Modifier.fillMaxWidth()) {
        GoogleMap(modifier = Modifier.fillMaxWidth(), cameraPositionState = cameraPosition) {
            Marker(state = MarkerState(position = cameraPosition.position.target))
        }

        AutoCompleteTextField(
            value = state.queryValue,
            onValueChange = { viewModel.onEvent(SelectLocationEvent.OnQueryChange(it)) },
            dropDownItems = state.places.map { it.formattedAddress },
            selectedIndex = state.selectedPlaceIndex,
            onItemSelected = { viewModel.onEvent(SelectLocationEvent.OnSelectPlace(it)) },
            hint = "Search"
        )

        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(topStart = SpaceMedium, topEnd = SpaceMedium),
        ) {
            Column(modifier = Modifier.padding(SpaceLarge)) {
                Text(text = state.placeToShow.name)
                Spacer(modifier = Modifier.height(SpaceMedium))
                Button(onClick = {
                    navController.navigate(AddBuddyScreenDestination)
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Pick this location")
                }
            }
        }
    }
}