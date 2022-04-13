package dev.ferrant.quickcoin.ui.search

import dev.ferrant.quickcoin.core.ui.StateReducer
import dev.ferrant.quickcoin.domain.model.Coin

sealed class SearchStateReducer : StateReducer<SearchViewState> {

    object Init : SearchStateReducer() {
        override fun reduce(initialState: SearchViewState): SearchViewState = initialState.copy()
    }

    class Search(
        val coinList: List<Coin> = listOf(),
        val errorMessage: String? = null,
    ) : SearchStateReducer() {
        override fun reduce(initialState: SearchViewState): SearchViewState =
            initialState.copy(
                coinListState = CoinListState.Success(coinList),
                message = errorMessage,
            )
    }

}
