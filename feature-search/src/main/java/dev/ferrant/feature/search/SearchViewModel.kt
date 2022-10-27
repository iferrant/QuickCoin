package dev.ferrant.feature.search

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ferrant.core.arch.BaseViewModel
import dev.ferrant.core.arch.StateReducer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@ExperimentalCoroutinesApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCoinsUseCase: SearchCoinsUseCase,
) : BaseViewModel<SearchViewEvent, SearchViewState>() {

    init {
        viewModelScope.launch {
            search()
        }
    }

    override fun createInitialState(): SearchViewState = SearchViewState()

    override fun Flow<SearchViewEvent>.handleEvent(): Flow<StateReducer<SearchViewState>> {
        val searchFlow = filterIsInstance<SearchViewEvent.OnSearch>()
            .debounce(300)
            .distinctUntilChanged()
            .flatMapConcat {
                searchCoinsUseCase(query = it.query)
                    .map<Response<List<Coin>>, SearchStateReducer> { response ->
                        when (response) {
                            is Response.Success -> SearchStateReducer.Search(coinList = response.data)
                            is Response.Error -> SearchStateReducer.Search(errorMessage = response.message)
                        }
                    }
            }
            .onStart { emit(SearchStateReducer.Skeletons) }

        return merge(
            searchFlow,
        )
    }

    fun search(query: String = "") {
        produceEvent(SearchViewEvent.OnSearch(query))
    }


}
