package dev.ferrant.core.data.repository

import dev.ferrant.core.data.model.Coin
import dev.ferrant.core.data.model.asModel
import dev.ferrant.core.network.QcNetworkDataSource
import dev.ferrant.core.network.model.NetworkCoin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val network: QcNetworkDataSource,
) : SearchRepository {

    override suspend fun search(query: String): Flow<List<Coin>> = flow {
        emit(network.search(query).coins.map(NetworkCoin::asModel))
    }

}
