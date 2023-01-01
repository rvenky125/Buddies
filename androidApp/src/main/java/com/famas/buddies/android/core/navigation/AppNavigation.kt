package com.famas.buddies.android.core.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import com.famas.buddies.android.core.theme.SpaceSmall
import com.famas.buddies.android.screens.NavGraphs
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun AppNavigation(mainNavigator: NavHostController, bottomSheetNavigator: BottomSheetNavigator) {
    val engine = rememberAnimatedNavHostEngine()
    ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator, sheetBackgroundColor = MaterialTheme.colorScheme.background, sheetElevation = SpaceSmall) {
        DestinationsNavHost(navGraph = NavGraphs.root, navController = mainNavigator, engine = engine)
    }
}