package dev.ferrant.quickcoin.data

import dev.ferrant.quickcoin.data.remote.ResponseResult
import dev.ferrant.quickcoin.data.remote.api.ApiHelper
import dev.ferrant.quickcoin.data.remote.getResponse
import dev.ferrant.quickcoin.data.remote.model.CoinsSearch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
) : SearchRepository {

    override suspend fun search(query: String): Flow<ResponseResult<CoinsSearch>> =
        apiHelper.search(query).getResponse()

}
