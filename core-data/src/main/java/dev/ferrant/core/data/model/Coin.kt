package dev.ferrant.core.data.model

import dev.ferrant.core.network.model.NetworkCoin

fun NetworkCoin.asModel() = Coin(
    id = id,
    large = large,
    marketCapRank = marketCapRank,
    name = name,
    symbol = symbol,
    thumb = thumb
)


data class Coin(
    val id: String,
    val large: String? = null,
    val marketCapRank: Int? = null,
    val name: String,
    val symbol: String,
    val thumb: String? = null,
)
