package com.famas.buddies.android.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Popup
import com.famas.buddies.android.util.toDp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@ExperimentalAnimationApi
@Composable
fun AutoCompleteTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    dropDownItems: List<String>,
    selectedIndex: Int?,
    onItemSelected: (Int?) -> Unit,
    hint: String,
    dropType: DropType = DropType.DropUp,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.primary),
) {
    val showPopUp = rememberSaveable { mutableStateOf(false) }
    val coroutine = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState().value
    val rotation = if (isFocused) 180f else 0f
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var offset by remember {
        mutableStateOf(IntOffset.Zero)
    }

    Column(modifier = modifier) {
        //We are using showPopUp value here to hide the content
        AnimatedVisibility(visible = (selectedIndex == null && isFocused) && showPopUp.value) {
            Popup(offset = offset, onDismissRequest = { showPopUp.value = false }) {
                ElevatedCard(
                    modifier = Modifier
                        .width(textFieldSize.width.toDp())
                        .heightIn(max = 200.dp)
                        .padding(vertical = 16.dp)
                        .onSizeChanged {
                            offset = offset.copy(
                                y = if (dropType == DropType.DropUp) -it.height else textFieldSize.height.roundToInt()
                            )
                        },
                ) {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        itemsIndexed(dropDownItems) { index, s ->
                            DropdownMenuItem(
                                onClick = {
                                    coroutine.launch {
                                        onItemSelected(index)
                                        focusManager.clearFocus(true)
                                        showPopUp.value = false
                                        onValueChange(s)
                                    }
                                },
                                enabled = selectedIndex != index,
                                text = {
                                    Text(text = s)
                                }
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = value,
            onValueChange = {
                if (!showPopUp.value) showPopUp.value = true
                onValueChange(it)
                if (selectedIndex != null) onItemSelected(null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    textFieldSize = it.size.toSize()
                }
                .focusRequester(focusRequester)
                .onFocusChanged {
                    if (it.isFocused) {
                        if (!showPopUp.value) showPopUp.value = true
                    }
                },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "drop down menu",
                    modifier = Modifier.graphicsLayer(
                        rotationZ = animateFloatAsState(
                            targetValue = rotation,
                            animationSpec = tween(800)
                        ).value
                    ),
                    tint = contentColorFor(backgroundColor = MaterialTheme.colorScheme.onSurface).copy(
                        0.8f
                    )
                )
            },
            label = {
                Text(text = hint)
            },
            interactionSource = interactionSource,
            textStyle = textStyle
        )
    }
}

enum class DropType {
    DropUp, DropDown
}