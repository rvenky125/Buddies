package com.famas.buddies.android.screens.screen_feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.famas.buddies.android.core.theme.SpaceMedium
import com.famas.buddies.android.core.theme.SpaceSemiSmall
import com.famas.buddies.android.core.theme.SpaceSmall
import com.famas.buddies.android.screens.destinations.SelectLocationScreenDestination
import com.famas.buddies.android.screens.screen_feed.components.BuddyListItem
import com.famas.buddies.feature_feed.interactors.FeedViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.navigate
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun FeedScreen(
    navController: NavController
) {
    val viewModel = getViewModel<FeedViewModel>()

    val state = viewModel.state.collectAsState().value

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = {
                navController.navigate(SelectLocationScreenDestination)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                Text(text = "Add Buddy")
            }
        }
    ) {
        LazyColumn(contentPadding = PaddingValues(horizontal = SpaceMedium), modifier = Modifier.padding(it)) {
            items(state.buddies) {
                BuddyListItem(buddy = it, modifier = Modifier.padding(vertical = SpaceSemiSmall))
            }
        }
    }
}