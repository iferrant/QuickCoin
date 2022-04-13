package dev.ferrant.quickcoin.domain.model

import dev.ferrant.quickcoin.data.remote.model.Coin as RemoteCoin

data class Coin(
    val id: String,
    val large: String?,
    val marketCapRank: Int?,
    val name: String,
    val symbol: String,
    val thumb: String?,
)

fun RemoteCoin.toCoin(): Coin =
    Coin(
        id = id,
        large = large,
        marketCapRank = marketCapRank,
        name = name,
        symbol = symbol,
        thumb = thumb,
    )
