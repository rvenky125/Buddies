package com.famas.buddies.android.core.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.famas.buddies.android.screens.NavGraphs
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun AppNavigation() {
    val engine = rememberAnimatedNavHostEngine()
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navigator = rememberAnimatedNavController(bottomSheetNavigator)
    ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {
        DestinationsNavHost(navGraph = NavGraphs.root, navController = navigator, engine = engine)
    }
}