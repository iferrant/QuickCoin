package dev.ferrant.quickcoin.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import dev.ferrant.quickcoin.domain.model.Coin
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun Search(
    viewModel: SearchViewModel = viewModel()
) {
    val viewState by viewModel.state.collectAsState()

    viewModel.search()

    when(val coinListState = viewState.coinListState) {
        is CoinListState.Success -> CoinList(coins = coinListState.coinList)
        is CoinListState.Loading -> Text(text = "Loading")
    }
}

@Composable
fun CoinList(coins: List<Coin>) {
    LazyColumn {
        items(coins) { coin ->
            CoinItem(coin = coin)
        }
    }
}

@Composable
fun CoinItem(coin: Coin) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .size(56.dp)
    ) {
        AsyncImage(
            model = coin.large,
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )
        Text(
            text = coin.name,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = coin.symbol,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@Preview
@Composable
fun Preview() {
    CoinList(
        coins = listOf(
            Coin(
                id = "id",
                name = "Ethereum",
                symbol = "ETH",
                marketCapRank = 10029,
                large = "",
                thumb = "",
            ),
            Coin(
                id = "id",
                name = "Solana",
                symbol = "SOL",
                marketCapRank = 10029,
                large = "",
                thumb = "",
            ),
            Coin(
                id = "id",
                name = "XRP",
                symbol = "XRP",
                marketCapRank = 10029,
                large = "",
                thumb = "",
            ),
            Coin(
                id = "id",
                name = "Bitcoin",
                symbol = "BTC",
                marketCapRank = 10029,
                large = "",
                thumb = "",
            ),
        )
    )
}
