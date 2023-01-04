package com.famas.buddies.android.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.famas.buddies.android.core.navigation.AppNavigation
import com.famas.buddies.android.core.theme.BuddiesTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator

class MainActivity : ComponentActivity() {
    @OptIn(
        ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class,
        ExperimentalMaterialNavigationApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val snackbarHostState = remember { SnackbarHostState() }

            val bottomSheetNavigator = rememberBottomSheetNavigator()
            val navigator = rememberAnimatedNavController(bottomSheetNavigator)
            val currentBackStack =
                navigator.currentBackStackEntryFlow.collectAsState(initial = null).value

            BuddiesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    }) {
                        AppNavigation(
                            mainNavigator = navigator,
                            bottomSheetNavigator = bottomSheetNavigator,
                            modifier = Modifier.padding(it),
                            snackbarHostState = snackbarHostState
                        )
                    }
                }
            }
        }
    }
}