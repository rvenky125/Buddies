package com.famas.buddies.android.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Feed
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.famas.buddies.android.core.navigation.AppNavigation
import com.famas.buddies.android.core.theme.BuddiesTheme
import com.famas.buddies.android.destinations.AddBuddyScreenDestination
import com.famas.buddies.android.destinations.ChatScreenDestination
import com.famas.buddies.android.destinations.FeedScreenDestination
import com.famas.buddies.android.destinations.ProfileScreenDestination
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.ramcosta.composedestinations.navigation.navigate

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

            val currentRoute = currentBackStack?.destination?.route

            BuddiesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    }, bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = currentRoute == FeedScreenDestination.route,
                                onClick = {
                                    navigator.navigate(FeedScreenDestination) {
                                        popUpTo(FeedScreenDestination.route)
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Feed,
                                        contentDescription = null
                                    )
                                }
                            )
                            NavigationBarItem(
                                selected = currentRoute == ChatScreenDestination.route,
                                onClick = {
                                    navigator.navigate(ChatScreenDestination) {
                                        popUpTo(FeedScreenDestination.route)
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Chat,
                                        contentDescription = null
                                    )
                                }
                            )
                            NavigationBarItem(
                                selected = currentRoute == ProfileScreenDestination.route,
                                onClick = {
                                    navigator.navigate(ProfileScreenDestination) {
                                        popUpTo(FeedScreenDestination.route)
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = null
                                    )
                                }
                            )
                        }
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