package dev.ferrant.quickcoin.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.ferrant.quickcoin.ui.search.Search
import dev.ferrant.quickcoin.ui.theme.QuickCoinTheme
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

@Composable
fun QuickCoinApp(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    QuickCoinTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = modifier.fillMaxSize(),
        ) {
            content()
        }
    }
}
