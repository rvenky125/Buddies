package com.famas.buddies.android.screens.screen_feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.famas.buddies.android.screens.destinations.SelectLocationScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate

@Composable
@Destination(start = true, route = "Buddies")
fun FeedScreen(
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = {
            navController.navigate(SelectLocationScreenDestination)
        }) {
            Text(text = "Add Buddy")
        }
    }
}