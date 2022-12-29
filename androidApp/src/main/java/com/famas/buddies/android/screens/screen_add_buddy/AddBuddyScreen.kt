package com.famas.buddies.android.screens.screen_add_buddy

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.famas.buddies.android.core.theme.SpaceLarge
import com.famas.buddies.android.core.theme.SpaceMedium
import com.famas.buddies.android.screens.screen_add_buddy.components.AddFilesLt
import com.famas.buddies.android.screens.screen_add_buddy.components.MapCard
import com.famas.buddies.android.util.getScreenSize
import com.famas.buddies.interactors.screen_add_buddy.AddBuddyEvent
import com.famas.buddies.interactors.screen_add_buddy.AddBuddyVM
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBuddyScreen(modifier: Modifier = Modifier) {
    val screenSize = getScreenSize()
    val viewModel: AddBuddyVM = getViewModel()
    val state = viewModel.state.collectAsState().value

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(SpaceMedium)
    ) {
        AddFilesLt(
            files = state.files,
            onAddFiles = { viewModel.onEvent(AddBuddyEvent.OnAddFiles(it)) },
            onRemoveFile = { viewModel.onEvent(AddBuddyEvent.OnRemoveFile(it)) })

        Spacer(modifier = Modifier.height(SpaceLarge))

        MapCard(
            mapVisible = state.showMap,
            setMapVisible = { viewModel.onEvent(AddBuddyEvent.OnChangeShowMap(it)) },
            onSelectLocation = {

            }
        )

        Spacer(modifier = Modifier.height(SpaceLarge))
        Text(
            text = "More about buddy",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = SpaceMedium)
        )
        OutlinedTextField(value = state.name, onValueChange = {
            viewModel.onEvent(AddBuddyEvent.OnNameChange(it))
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = SpaceMedium), label = {
            Text(text = "Buddy name \uD83D\uDC36")
        })
        OutlinedTextField(
            value = state.note,
            onValueChange = {
                viewModel.onEvent(AddBuddyEvent.OnNoteChange(it))
            },
            maxLines = 5,
            modifier = Modifier
                .fillMaxWidth()
                .height(screenSize.height * 0.20f),
            label = {
                Text(text = "Add Note if any")
            }
        )

        Button(
            onClick = { /*TODO*/ }, modifier = Modifier
                .align(Alignment.End)
                .padding(top = SpaceMedium)
        ) {
            Text(text = "Add Buddy")
        }
    }
}