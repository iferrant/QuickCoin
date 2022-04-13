package dev.ferrant.quickcoin.data

import dev.ferrant.quickcoin.data.remote.ResponseResult
import dev.ferrant.quickcoin.data.remote.model.CoinsSearch
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun search(query: String): Flow<ResponseResult<CoinsSearch>>
}
