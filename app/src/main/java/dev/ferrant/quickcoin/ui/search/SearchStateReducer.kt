package dev.ferrant.quickcoin.ui.search

import dev.ferrant.quickcoin.core.ui.StateReducer
import dev.ferrant.quickcoin.domain.model.Coin
import dev.ferrant.quickcoin.ui.search.model.CoinListItem

sealed class SearchStateReducer : StateReducer<SearchViewState> {

    object Skeletons : SearchStateReducer() {
        override fun reduce(initialState: SearchViewState) =
            initialState.copy(
                coinListState = CoinListState.Loading(
                    coinList = arrayListOf<CoinListItem.Skeleton>().apply {
                        for (i in 0..20) add(CoinListItem.Skeleton)
                    },
                )
            )
    }

    class Search(
        private val coinList: List<Coin> = listOf(),
        private val errorMessage: String? = null,
    ) : SearchStateReducer() {
        override fun reduce(initialState: SearchViewState): SearchViewState =
            initialState.copy(
                coinListState = CoinListState.Success(coinList.map { CoinListItem.Coin(it) }),
                message = errorMessage,
            )
    }

}
