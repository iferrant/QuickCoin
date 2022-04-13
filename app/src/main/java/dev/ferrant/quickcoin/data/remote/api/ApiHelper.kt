package dev.ferrant.quickcoin.data.remote.api

import dev.ferrant.quickcoin.data.remote.model.CoinsSearch
import retrofit2.Response


interface ApiHelper {
    suspend fun search(query: String): Response<CoinsSearch>
}
