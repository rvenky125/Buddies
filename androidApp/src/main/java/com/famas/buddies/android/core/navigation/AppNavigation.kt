package com.famas.buddies.android.core.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.famas.buddies.android.core.theme.SpaceSmall
import com.famas.buddies.android.screens.NavGraphs
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.dependency

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun AppNavigation(
    mainNavigator: NavHostController,
    bottomSheetNavigator: BottomSheetNavigator,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    val engine = rememberAnimatedNavHostEngine()
    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetBackgroundColor = MaterialTheme.colorScheme.background,
        sheetElevation = SpaceSmall,
        sheetShape = RoundedCornerShape(topStart = SpaceSmall, topEnd = SpaceSmall),
    ) {
        DestinationsNavHost(
            navGraph = NavGraphs.root,
            navController = mainNavigator,
            engine = engine,
            modifier = modifier,
            dependenciesContainerBuilder = {
                dependency(snackbarHostState)
            }
        )
    }
}