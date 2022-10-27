package dev.ferrant.core.network

import dev.ferrant.core.network.model.NetworkCoinSearch

interface QcNetworkDataSource {
    suspend fun search(query: String): NetworkCoinSearch
}
