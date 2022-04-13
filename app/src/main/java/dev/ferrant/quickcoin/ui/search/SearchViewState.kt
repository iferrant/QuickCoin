package dev.ferrant.quickcoin.ui.search

import dev.ferrant.quickcoin.core.ui.ViewState
import dev.ferrant.quickcoin.domain.model.Coin

data class SearchViewState(
    val coinListState: CoinListState = CoinListState.Loading,
    val message: String? = null,
): ViewState

sealed class CoinListState {
    object Loading: CoinListState()
    data class Success(val coinList: List<Coin>): CoinListState()
}
