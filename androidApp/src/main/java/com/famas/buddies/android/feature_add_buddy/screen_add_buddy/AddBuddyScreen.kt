package com.famas.buddies.android.feature_add_buddy.screen_add_buddy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.window.Dialog
import com.famas.buddies.android.core.theme.SpaceLarge
import com.famas.buddies.android.core.theme.SpaceMedium
import com.famas.buddies.android.destinations.FeedScreenDestination
import com.famas.buddies.android.feature_add_buddy.screen_add_buddy.components.AddFilesLt
import com.famas.buddies.android.util.getScreenSize
import com.famas.buddies.feature_add_buddy.add_buddy_details.interactors.AddBuddyEvent
import com.famas.buddies.feature_add_buddy.add_buddy_details.interactors.AddBuddyVM
import com.famas.buddies.feature_add_buddy.select_location_map.domain.model.LocationModel
import com.famas.buddies.util.UiEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import com.ramcosta.composedestinations.spec.DestinationStyle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination(style = DestinationStyle.BottomSheet::class, route = "Add Buddy", navArgsDelegate = LocationModel::class)
fun AddBuddyScreen(pickedLocationModel: LocationModel, snackbarHostState: SnackbarHostState, navigator: DestinationsNavigator, modifier: Modifier = Modifier) {
    val viewModel: AddBuddyVM = getViewModel()
    val state = viewModel.state.collectAsState().value
    val coroutine = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.setPickedLocation(pickedLocationModel)
        viewModel.uiEvent.collectLatest {
            when (it) {
                is UiEvent.ShowMessage -> {
                    coroutine.launch {
                        snackbarHostState.showSnackbar(it.message)
                    }
                }
                is UiEvent.Navigate -> {

                }
                UiEvent.GoBack -> {
                    navigator.navigate(FeedScreenDestination) {
                        popUpTo(FeedScreenDestination) {
                            this.inclusive = false
                        }
                    }
                }
            }
        }
    })

    Column(
        modifier = modifier
            .padding(SpaceMedium)
    ) {
        AddFilesLt(
            files = state.files,
            onAddFiles = { viewModel.onEvent(AddBuddyEvent.OnAddFiles(it)) },
            onRemoveFile = { viewModel.onEvent(AddBuddyEvent.OnRemoveFile(it)) })

        Spacer(modifier = Modifier.height(SpaceLarge))

        Spacer(modifier = Modifier.height(SpaceLarge))
        Text(
            text = "More about buddy",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = SpaceMedium)
        )
        OutlinedTextField(
            value = state.name, onValueChange = {
                viewModel.onEvent(AddBuddyEvent.OnNameChange(it))
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = SpaceMedium), label = {
                Text(text = "Buddy name \uD83D\uDC36")
            }, maxLines = 1, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
        OutlinedTextField(
            value = state.note,
            onValueChange = {
                viewModel.onEvent(AddBuddyEvent.OnNoteChange(it))
            },
            maxLines = 5,
            modifier = Modifier
                .fillMaxWidth(),
            label = {
                Text(text = "Add Note if any")
            }
        )

        Button(
            onClick = { viewModel.onEvent(AddBuddyEvent.OnSubmit) }, modifier = Modifier
                .align(Alignment.End)
                .padding(top = SpaceMedium)
        ) {
            Text(text = "Add Buddy")
        }

        if (state.loading) {
            Dialog(onDismissRequest = { /*TODO*/ }) {
                CircularProgressIndicator()
            }
        }
    }
}