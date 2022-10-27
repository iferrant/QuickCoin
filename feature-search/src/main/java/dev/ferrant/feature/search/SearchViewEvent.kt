package dev.ferrant.feature.search

import dev.ferrant.core.arch.ViewEvent


sealed class SearchViewEvent: ViewEvent {
    object OnInit: SearchViewEvent()
    data class OnSearch(val query: String): SearchViewEvent()
    data class OnSelectCoin(val coinId: String): SearchViewEvent()
}
