package dev.ferrant.quickcoin.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.ferrant.quickcoin.ui.search.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun QuickCoinApp(
    appState: QuickCoinAppState = rememberQuickCoinAppState()
) {

    NavHost(
        navController = appState.navController,
        startDestination = Screen.Search.route,
    ) {
        composable(Screen.Search.route) {
            Search(hiltViewModel())
        }
    }

}
