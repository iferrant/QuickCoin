package dev.ferrant.feature.search

import dev.ferrant.core.arch.ViewState
import dev.ferrant.core.model.CoinListItem

data class SearchViewState(
    val coinListState: CoinListState = CoinListState.Loading(listOf()),
    val message: String? = null,
): ViewState

sealed class CoinListState {
    data class Loading(val coinList: List<dev.ferrant.core.model.CoinListItem.Skeleton>): CoinListState()
    data class Success(val coinList: List<dev.ferrant.core.model.CoinListItem.Coin>): CoinListState()
}
