package com.famas.buddies.android.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun getScreenSize(): ScreenSize {
    val configuration = LocalConfiguration.current
    return ScreenSize(configuration.screenWidthDp.dp, configuration.screenHeightDp.dp)
}

data class ScreenSize(
    val width: Dp,
    val height: Dp
)