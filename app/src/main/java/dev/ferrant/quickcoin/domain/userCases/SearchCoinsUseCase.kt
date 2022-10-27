package dev.ferrant.quickcoin.domain.userCases

import dev.ferrant.core.data.repository.SearchRepository
import dev.ferrant.quickcoin.data.remote.ResponseResult
import dev.ferrant.quickcoin.domain.Response
import dev.ferrant.quickcoin.domain.model.Coin
import dev.ferrant.quickcoin.domain.model.toCoin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchCoinsUseCase @Inject constructor(
    private val searchRepository: dev.ferrant.core.data.repository.SearchRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    suspend operator fun invoke(query: String): Flow<Response<List<Coin>>> =
        withContext(dispatcher) {
            searchRepository
                .search(query)
                .map { response ->
                    when(response) {
                        is ResponseResult.Success ->
                            Response.Success(response.data.coins.map { it.toCoin() })
                        is ResponseResult.Error ->
                            Response.Error(response.message)
                    }
                }
        }

}
