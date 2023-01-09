package com.famas.buddies.android.feature_feed.buddy_overview

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.famas.buddies.android.core.theme.*
import com.famas.buddies.android.feature_feed.buddy_overview.components.ParallaxImagePager
import com.famas.buddies.feature_add_buddy.add_buddy_details.interactors.Gender
import com.famas.buddies.feature_feed.feed_list.domain.model.BuddyDto
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.spec.DestinationStyle


@OptIn(ExperimentalPagerApi::class)
@Composable
@Destination(style = DestinationStyle.BottomSheet::class)
fun BuddyOverview(buddy: BuddyDto) {
    val cameraPositionState = rememberCameraPositionState()
    val markerState = rememberMarkerState(position = LatLng(buddy.lat, buddy.lng))

    LaunchedEffect(key1 = Unit, block = {
        Log.d("myTag", "${LatLng(buddy.lat, buddy.lng)}")
        cameraPositionState.position = CameraPosition(
            LatLng(buddy.lat, buddy.lng),
            9f,
            cameraPositionState.position.tilt,
            cameraPositionState.position.bearing
        )
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(SpaceLarge)
    ) {
        ParallaxImagePager(buddy.files)
        Row {
            Text(
                text = buddy.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.alignByBaseline()
            )
            Text(
                text = "(breed)",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .alignByBaseline()
                    .padding(start = SpaceVerySmall)
            )
        }
        OutlinedCard(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
            modifier = Modifier.padding(bottom = SpaceMedium)
        ) {
            Text(
                text = "${
                    Gender.values().firstOrNull { it.id == buddy.gender }?.name ?: "N/A"
                }, ${buddy.age} Years",
                modifier = Modifier.padding(
                    vertical = SpaceVerySmall,
                    horizontal = SpaceSmall
                ),
                style = MaterialTheme.typography.labelSmall
            )
        }
        Text(text = "Note: ${buddy.note}", modifier = Modifier.padding(bottom = SpaceMedium))
        Text(text = "Address: ${buddy.address}", modifier = Modifier.padding(bottom = SpaceVerySmall))

        GoogleMap(
            modifier = Modifier.fillMaxWidth(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(
                compassEnabled = false,
                indoorLevelPickerEnabled = false,
                mapToolbarEnabled = false,
                myLocationButtonEnabled = false,
                rotationGesturesEnabled = false,
                scrollGesturesEnabled = false,
                scrollGesturesEnabledDuringRotateOrZoom = false,
                tiltGesturesEnabled = false,
            )
        ) {
            Marker(state = markerState)
        }
    }
}