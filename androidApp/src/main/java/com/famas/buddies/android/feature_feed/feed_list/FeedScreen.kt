package com.famas.buddies.android.feature_feed.feed_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.famas.buddies.android.core.theme.SpaceMedium
import com.famas.buddies.android.core.theme.SpaceSemiSmall
import com.famas.buddies.android.destinations.BuddyOverviewDestination
import com.famas.buddies.android.destinations.SelectLocationScreenDestination
import com.famas.buddies.android.feature_feed.feed_list.components.BuddyListItem
import com.famas.buddies.feature_feed.feed_list.domain.model.BuddyDto
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

    LaunchedEffect(key1 = Unit, block = {
        viewModel.syncBuddies()
    })

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = {
                navController.navigate(SelectLocationScreenDestination)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                Text(text = "Add Buddy")
            }
        },
        topBar = {
            TopAppBar(title = {
                Text(text = "Buddies")
            })
        }
    ) { values ->
        LazyColumn(
            contentPadding = PaddingValues(horizontal = SpaceMedium),
            modifier = Modifier
                .fillMaxSize()
                .padding(values)
        ) {
            items(state.buddies) {
                BuddyListItem(buddy = it, modifier = Modifier.padding(vertical = SpaceSemiSmall)) {
                    navController.navigate(
                        BuddyOverviewDestination(
                            buddy = it
                        )
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }


    if (state.loading) {
        Dialog(onDismissRequest = { /*TODO*/ }) {
            CircularProgressIndicator()
        }
    }
}