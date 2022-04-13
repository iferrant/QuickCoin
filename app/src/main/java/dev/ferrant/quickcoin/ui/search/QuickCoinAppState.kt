package dev.ferrant.quickcoin.ui.search

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object Search : Screen("search")
}

@Composable
fun rememberQuickCoinAppState(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) = remember(navController, context) {
    QuickCoinAppState(navController, context)
}

class QuickCoinAppState(
    val navController: NavHostController,
    private val context: Context,
) {

    fun navigateBack() {
        navController.popBackStack()
    }

}
