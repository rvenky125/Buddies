package com.famas.buddies.android.screens.screen_add_buddy.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.famas.buddies.android.util.getScreenSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapCard(
    mapVisible: Boolean,
    modifier: Modifier = Modifier,
    setMapVisible: (Boolean) -> Unit,
    onSelectLocation: () -> Unit,
) {
    val screenSize = getScreenSize()

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedCard(onClick = {
            setMapVisible(false)
        }, modifier = modifier
            .fillMaxWidth()
            .height(screenSize.height * 0.20f)) {
            Text(text = "Pick Location \uD83D\uDCCD")
        }
    }

}