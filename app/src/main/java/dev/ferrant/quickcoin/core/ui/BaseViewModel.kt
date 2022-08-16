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
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = initialState
            )

    abstract fun createInitialState(): VS

    abstract fun Flow<VE>.handleEvent(): Flow<StateReducer<VS>>

    fun produceEvent(event: VE) = viewModelScope.launch { viewEventFlow.emit(event) }

    init {
        viewEventFlow
            .handleEvent()
            .scan(initialState) { viewState, change -> change.reduce(viewState) }
            .onEach { setState(it) }
            .launchIn(viewModelScope)
    }

    private fun setState(state: VS) {
        _viewStateFlow.value = state
    }

}
