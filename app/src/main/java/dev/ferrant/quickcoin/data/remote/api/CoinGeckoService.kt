package dev.ferrant.quickcoin.data.remote.api

import dev.ferrant.quickcoin.data.remote.model.CoinsSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinGeckoService {

    @GET("search")
    suspend fun search(@Query("query") query: String = ""): Response<CoinsSearch>

}
