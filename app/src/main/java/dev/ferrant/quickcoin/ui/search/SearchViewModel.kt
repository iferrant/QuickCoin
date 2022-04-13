package dev.ferrant.quickcoin.ui.search

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ferrant.quickcoin.core.ui.BaseViewModel
import dev.ferrant.quickcoin.core.ui.StateReducer
import dev.ferrant.quickcoin.domain.Response
import dev.ferrant.quickcoin.domain.model.Coin
import dev.ferrant.quickcoin.domain.userCases.SearchCoinsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCoinsUseCase: SearchCoinsUseCase,
) : BaseViewModel<SearchViewEvent, SearchViewState>() {

    override fun createInitialState(): SearchViewState = SearchViewState()

    override fun handleEvent(event: SearchViewEvent): Flow<StateReducer<SearchViewState>> {
        val initFlow = flowOf(event)
            .filterIsInstance<SearchViewEvent.OnInit>()
            .map { SearchStateReducer.Init }

        val searchFlow = flowOf(event)
            .filterIsInstance<SearchViewEvent.OnSearch>()
            .flatMapConcat {
                searchCoinsUseCase(query = it.query)
                    .map<Response<List<Coin>>, SearchStateReducer> { response ->
                        when (response) {
                            is Response.Success -> SearchStateReducer.Search(coinList = response.data)
                            is Response.Error -> SearchStateReducer.Search(errorMessage = response.message)
                        }
                    }
            }

        return merge(
            initFlow,
            searchFlow,
        )
    }

    fun search(query: String = "a") {
        produceEvent(SearchViewEvent.OnSearch(query))
    }


}
