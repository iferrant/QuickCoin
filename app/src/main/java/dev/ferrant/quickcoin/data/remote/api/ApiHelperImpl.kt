package dev.ferrant.quickcoin.data.remote.api

import dev.ferrant.quickcoin.data.remote.model.NetworkCoinSearch
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val coinGeckoService: dev.ferrant.core.network.retrofit.CoinGeckoService,
): dev.ferrant.core.network.ApiHelper {

    override suspend fun search(query: String): Response<NetworkCoinSearch> =
        coinGeckoService.search(query)
}
