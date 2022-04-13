package dev.ferrant.quickcoin.core.ui

interface StateReducer<VS: ViewState> {
    fun reduce(initialState: VS): VS
}
