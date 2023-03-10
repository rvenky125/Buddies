package com.famas.buddies.android.feature_add_buddy.screen_select_location

import android.util.Log

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.famas.buddies.android.R
import com.famas.buddies.android.core.theme.SpaceLarge
import com.famas.buddies.android.core.theme.SpaceMedium
import com.famas.buddies.android.destinations.AddBuddyScreenDestination
import com.famas.buddies.android.util.getScreenSize
import com.famas.buddies.feature_add_buddy.add_buddy_details.interactors.AddBuddyNavArgs
import com.famas.buddies.feature_add_buddy.select_location_map.domain.model.LocationModel
import com.famas.buddies.feature_add_buddy.select_location_map.interactors.SelectLocationEvent
import com.famas.buddies.feature_add_buddy.select_location_map.interactors.SelectLocationVM
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel

@Composable
@Destination(route = "Pick Location")
fun SelectLocationScreen(
    navController: NavController
) {
    val screenSize = getScreenSize()

    val viewModel: SelectLocationVM = getViewModel()
    val cameraPosition = rememberCameraPositionState()

    val state = viewModel.state.collectAsState().value

    val context = LocalContext.current

    val coroutine = rememberCoroutineScope()

    LaunchedEffect(key1 = cameraPosition.position.target, block = {
        delay(500)
        viewModel.onEvent(
            SelectLocationEvent.OnChangeLocation(
                LocationModel(
                    cameraPosition.position.target.latitude,
                    cameraPosition.position.target.longitude
                )
            )
        )
    })

    LaunchedEffect(key1 = state.places, block = {
        Log.d("myTag", "${state.places.size}")
    })

    Box(modifier = Modifier.fillMaxWidth()) {
        GoogleMap(modifier = Modifier.fillMaxWidth(), cameraPositionState = cameraPosition) {
//            Marker(state = MarkerState(position = cameraPosition.position.target))
        }

        Image(
            painter = painterResource(id = R.drawable.map_pin),
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 33.dp)
                .size(33.dp)
                .align(Alignment.Center)
        )

        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(topStart = SpaceMedium, topEnd = SpaceMedium),
        ) {
            Column(modifier = Modifier.padding(SpaceLarge)) {
                Text(text = if (state.loading) "Loading..." else state.placeToShow.name)
                Spacer(modifier = Modifier.height(SpaceMedium))
                Button(onClick = {
                    val target = cameraPosition.position.target
                    navController.navigate(
                        AddBuddyScreenDestination(
                            addBuddyNavArgs = AddBuddyNavArgs(
                                location = LocationModel(
                                    target.latitude,
                                    target.longitude
                                ),
                                place = state.placeToShow
                            )
                        )
                    )
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Pick this location")
                }
            }
        }
    }
}


//ExposedDropdownMenuBox(
//expanded = state.selectedPlace == null || isExpanded,
//onExpandedChange = {
//    isExpanded = it
//},
//modifier = Modifier.fillMaxWidth()
//) {
//    TextField(
//        value = state.queryValue,
//        onValueChange = {
//            if (state.selectedPlace != null) {
//                viewModel.onEvent(SelectLocationEvent.OnSelectPlace(null))
//            }
//            viewModel.onEvent(SelectLocationEvent.OnQueryChange(it))
//        },
//        label = { Text("Label") },
//        trailingIcon = {
//            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
//        },
//        colors = ExposedDropdownMenuDefaults.textFieldColors(),
//        modifier = Modifier
//            .menuAnchor()
//            .fillMaxWidth()
//    )
//    ExposedDropdownMenu(
//        expanded = state.selectedPlace == null || isExpanded,
//        onDismissRequest = {
//            isExpanded = false
//        },
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        state.places.forEachIndexed { index, candidate ->
//            DropdownMenuItem(
//                text = {
//                    Text(text = candidate.name)
//                },
//                onClick = { viewModel.onEvent(SelectLocationEvent.OnSelectPlace(index)) },
//                modifier = Modifier.fillMaxWidth()
//            )
//        }
//    }
//}
