package dev.ferrant.quickcoin.ui.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.ferrant.quickcoin.domain.model.Coin
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun Search(
    viewModel: SearchViewModel = viewModel()
) {
    val viewState by viewModel.state.collectAsState()

    viewModel.search()

    Surface(Modifier.fillMaxSize()) {
        when(viewState.coinListState) {
            is CoinListState.Success -> CoinItem(
                coin = (viewState.coinListState as CoinListState.Success).coinList[0]
            )
            is CoinListState.Loading -> Text(text = "Loading")
        }

    }
}

@Composable
fun CoinItem(coin: Coin) {
    Text(text = coin.name)
}

@Preview
@Composable
fun Preview() {
    CoinItem(
        coin = Coin(
            id = "id",
            name = "Ethereum",
            symbol = "Symbol",
            marketCapRank = 10029,
            large = "",
            thumb = "",
        )
    )
}
