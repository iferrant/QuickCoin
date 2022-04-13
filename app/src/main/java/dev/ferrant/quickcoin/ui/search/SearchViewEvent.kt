package dev.ferrant.quickcoin.ui.search

import dev.ferrant.quickcoin.core.ui.ViewEvent

sealed class SearchViewEvent: ViewEvent {
    object OnInit: SearchViewEvent()
    data class OnSearch(val query: String): SearchViewEvent()
    data class OnSelectCoin(val coinId: String): SearchViewEvent()
}
