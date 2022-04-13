package dev.ferrant.quickcoin.core.ui

interface BaseView<VS: ViewState> {
    fun renderState(state: VS)
}
