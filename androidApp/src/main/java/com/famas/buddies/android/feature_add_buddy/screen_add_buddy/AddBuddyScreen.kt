package com.famas.buddies.android.feature_add_buddy.screen_add_buddy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.famas.buddies.android.core.theme.SpaceLarge
import com.famas.buddies.android.core.theme.SpaceMedium
import com.famas.buddies.android.destinations.FeedScreenDestination
import com.famas.buddies.android.feature_add_buddy.screen_add_buddy.components.AddFilesLt
import com.famas.buddies.android.util.getScreenSize
import com.famas.buddies.feature_add_buddy.add_buddy_details.interactors.AddBuddyEvent
import com.famas.buddies.feature_add_buddy.add_buddy_details.interactors.AddBuddyNavArgs
import com.famas.buddies.feature_add_buddy.add_buddy_details.interactors.AddBuddyVM
import com.famas.buddies.feature_add_buddy.add_buddy_details.interactors.Gender
import com.famas.buddies.feature_add_buddy.select_location_map.domain.model.LocationModel
import com.famas.buddies.util.UiEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navargs.DestinationsNavTypeSerializer
import com.ramcosta.composedestinations.navargs.NavTypeSerializer
import com.ramcosta.composedestinations.navargs.utils.encodeForRoute
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import com.ramcosta.composedestinations.spec.DestinationStyle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination(
    style = DestinationStyle.BottomSheet::class,
    route = "Add Buddy",
//    navArgsDelegate = AddBuddyNavArgs::class,
)
fun AddBuddyScreen(
    addBuddyNavArgs: AddBuddyNavArgs,
    snackbarHostState: SnackbarHostState,
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier
) {
    val viewModel: AddBuddyVM = getViewModel()
    val state = viewModel.state.collectAsState().value
    val coroutine = rememberCoroutineScope()
    val showAgeDropDown = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.setPickedLocation(addBuddyNavArgs)
        viewModel.uiEvent.collect {
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = SpaceMedium), verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = state.age,
                onValueChange = {
                    viewModel.onEvent(AddBuddyEvent.OnAgeChange(it))
                },
                modifier = Modifier
                    .weight(0.4f)
                    .padding(end = SpaceMedium),
                label = {
                    Text(text = "Age")
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                )
            )

            Column(modifier = Modifier.weight(0.6f)) {
                Card(
                    onClick = {
                        showAgeDropDown.value = !showAgeDropDown.value
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .height(57.dp),
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = state.gender?.name ?: "Select Gender",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
                DropdownMenu(expanded = showAgeDropDown.value, onDismissRequest = {
                    showAgeDropDown.value = false
                }, modifier = Modifier.fillMaxWidth(0.55f)) {
                    Gender.values().forEach {
                        DropdownMenuItem(text = {
                            Text(
                                text = it.name,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }, onClick = {
                            viewModel.onEvent(AddBuddyEvent.OnGenderChange(it))
                            showAgeDropDown.value = false
                        })
                    }
                }
            }
        }
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