package dev.ferrant.core.data.repository

import dev.ferrant.core.data.model.Coin
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun search(query: String): Flow<List<Coin>>
}
