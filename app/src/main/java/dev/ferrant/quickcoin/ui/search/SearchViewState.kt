package dev.ferrant.quickcoin.ui.search

import dev.ferrant.quickcoin.core.ui.ViewState
import dev.ferrant.quickcoin.ui.search.model.CoinListItem

data class SearchViewState(
    val coinListState: CoinListState = CoinListState.Loading(listOf()),
    val message: String? = null,
): ViewState

sealed class CoinListState {
    data class Loading(val coinList: List<CoinListItem.Skeleton>): CoinListState()
    data class Success(val coinList: List<CoinListItem.Coin>): CoinListState()
}
