package dev.ferrant.quickcoin.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import dev.ferrant.quickcoin.R
import dev.ferrant.quickcoin.domain.model.Coin
import dev.ferrant.quickcoin.ui.common.SearchBar
import dev.ferrant.quickcoin.ui.common.loadingShimmerEffect
import dev.ferrant.quickcoin.ui.search.model.CoinListItem
import dev.ferrant.quickcoin.ui.theme.QuickCoinTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun Search(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = viewModel(),
) {
    val viewState by viewModel.state.collectAsState()

    SearchCoin(
        state = viewState.coinListState,
        modifier = modifier,
    ) {
        viewModel.search(query = it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchCoin(
    state: CoinListState,
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.add_coin))
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        modifier = modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    start = 16.dp,
                    end = 16.dp,
                ),
        ) {

            SearchBar(
                modifier = Modifier
                    .fillMaxWidth(),
            ) { onSearch(it) }

            when (state) {
                is CoinListState.Success -> CoinList(coins = state.coinList)
                is CoinListState.Loading -> CoinListSkeleton(coins = state.coinList)
            }

        }
    }
}

@Composable
fun CoinListSkeleton(
    coins: List<CoinListItem.Skeleton>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(items = coins) { CoinItemSkeleton() }
    }
}

@Composable
fun CoinList(
    coins: List<CoinListItem.Coin>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(items = coins, key = { it.coin.id }) {
            CoinItem(coin = it.coin)
        }
    }
}

@Composable
fun CoinItem(
    coin: Coin,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .size(56.dp),
    ) {
        AsyncImage(
            model = coin.large,
            contentDescription = null,
            modifier = Modifier.size(32.dp),
        )

        Text(
            text = coin.name,
            style = MaterialTheme.typography.titleMedium,
        )

        Text(
            text = coin.symbol,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@Composable
fun CoinItemSkeleton() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .size(56.dp),
    ) {

        // Image
        Spacer(modifier = Modifier
            .size(32.dp)
            .background(
                brush = loadingShimmerEffect(),
                shape = RoundedCornerShape(4.dp),
            )
        )

        // Name
        Spacer(modifier = Modifier
            .size(
                width = 100.dp,
                height = 16.dp,
            )
            .background(
                brush = loadingShimmerEffect(),
                shape = RoundedCornerShape(4.dp),
            )
        )

        // Symbol
        Spacer(modifier = Modifier
            .size(
                width = 72.dp,
                height = 16.dp,
            )
            .background(
                brush = loadingShimmerEffect(),
                shape = RoundedCornerShape(4.dp),
            )
        )
    }
}

@Preview
@Composable
fun CoinItemPreview() {
    val coin = Coin(
        id = "id",
        name = "Ethereum",
        symbol = "ETH",
        marketCapRank = 10029,
        large = "",
        thumb = "",
    )
    CoinItem(coin = coin)
}

@Preview
@Composable
fun CoinItemSkeletonPreview() {
    CoinItemSkeleton()
}

@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    QuickCoinTheme {
        SearchCoin(
            state = CoinListState.Success(listOf(
                CoinListItem.Coin(
                    Coin(
                        id = "id1",
                        name = "Ethereum",
                        symbol = "ETH",
                        marketCapRank = 10029,
                        large = "",
                        thumb = "",
                    )
                ),
                CoinListItem.Coin(
                    Coin(
                        id = "id2",
                        name = "Solana",
                        symbol = "SOL",
                        marketCapRank = 10029,
                        large = "",
                        thumb = "",
                    )
                ),
                CoinListItem.Coin(
                    Coin(
                        id = "id3",
                        name = "XRP",
                        symbol = "XRP",
                        marketCapRank = 10029,
                        large = "",
                        thumb = "",
                    )
                ),
                CoinListItem.Coin(
                    Coin(
                        id = "id4",
                        name = "Bitcoin",
                        symbol = "BTC",
                        marketCapRank = 10029,
                        large = "",
                        thumb = "",
                    )
                ),
            )),
        ) { }
    }
}
