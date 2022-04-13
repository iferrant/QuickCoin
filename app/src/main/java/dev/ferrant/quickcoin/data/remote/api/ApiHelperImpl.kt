package dev.ferrant.quickcoin.data.remote.api

import dev.ferrant.quickcoin.data.remote.model.CoinsSearch
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val coinGeckoService: CoinGeckoService,
): ApiHelper {

    override suspend fun search(query: String): Response<CoinsSearch> =
        coinGeckoService.search(query)
}
