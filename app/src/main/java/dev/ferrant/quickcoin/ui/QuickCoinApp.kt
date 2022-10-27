package dev.ferrant.quickcoin.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.ferrant.feature_search.Search
import dev.ferrant.quickcoin.ui.theme.QuickCoinTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalCoroutinesApi
@Composable
fun QuickCoinApp(
    appState: QuickCoinAppState = rememberQuickCoinAppState()
) {
    QuickCoinTheme {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.fillMaxSize()
        ) { padding ->

            NavHost(
                navController = appState.navController,
                startDestination = Screen.Search.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(Screen.Search.route) {
                    dev.ferrant.feature_search.Search(viewModel = hiltViewModel())
                }
            }

        }
    }
}
