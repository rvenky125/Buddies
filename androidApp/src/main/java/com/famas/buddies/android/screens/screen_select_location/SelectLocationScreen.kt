package com.famas.buddies.android.screens.screen_select_location

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.famas.buddies.android.core.theme.SpaceMedium
import com.famas.buddies.android.screens.destinations.AddBuddyScreenDestination
import com.famas.buddies.android.util.getScreenSize
import com.famas.buddies.feature_select_map.interactors.SelectLocationVM
import com.google.maps.android.compose.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.DestinationStyle
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination()
fun SelectLocationScreen(
    navController: NavController
) {
    val screenSize = getScreenSize()

    val viewModel: SelectLocationVM = getViewModel()

    val mapProperties = remember {
        MapProperties(isMyLocationEnabled = true)
    }

    LaunchedEffect(key1 = Unit, block = {
        Log.d("myTag", "calling get location")
        viewModel.fetchForLocation()
    })

    val cameraPosition = rememberCameraPositionState()
    // Specify the fields to return.
//    val placeFields = listOf(Place.Field.NAME, Place.Field.RATING, Place.Field.OPENING_HOURS)

    Box(modifier = Modifier.fillMaxWidth()) {
        GoogleMap(modifier = Modifier.fillMaxWidth(), cameraPositionState = cameraPosition) {
            Marker(state = MarkerState(position = cameraPosition.position.target))
        }

        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(topStart = SpaceMedium, topEnd = SpaceMedium),
        ) {
            Column(modifier = Modifier.padding(SpaceMedium)) {
                Text(text = "Address")
                Button(onClick = {
                    navController.navigate(AddBuddyScreenDestination)
                }) {
                    Text(text = "Pick my current location")
                }
            }
        }
    }
}