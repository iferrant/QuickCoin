package dev.ferrant.quickcoin.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
abstract class BaseViewModel<VE : ViewEvent, VS : ViewState> : ViewModel() {

    private val initialState: VS by lazy { createInitialState() }

    private val viewEventFlow = MutableSharedFlow<VE>()

    private val _viewStateFlow = MutableStateFlow(initialState)
    val state: StateFlow<VS>
        get() = _viewStateFlow

    abstract fun createInitialState(): VS

    abstract fun handleEvent(event: VE): Flow<StateReducer<VS>>

    fun produceEvent(event: VE) = viewModelScope.launch { viewEventFlow.emit(event) }

    init {
        viewModelScope.launch {
            viewEventFlow
                .flatMapLatest { handleEvent(it) }
                .scan(initialState) { viewState, change -> change.reduce(viewState) }
                .onEach { setState(it) }
                .launchIn(viewModelScope)
        }
    }

    private fun setState(state: VS) {
        _viewStateFlow.value = state
    }

}
