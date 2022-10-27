package dev.ferrant.core.network.model

import com.squareup.moshi.Json

data class NetworkCoinSearch (
    @Json(name = "coins")
    val coins: List<NetworkCoin>,
)

data class NetworkCoin (
    @Json(name = "id")
    val id: String,

    @Json(name = "large")
    val large: String?,

    @Json(name = "market_cap_rank")
    val marketCapRank: Int?,

    @Json(name = "name")
    val name: String,

    @Json(name = "symbol")
    val symbol: String,

    @Json(name = "thumb")
    val thumb: String?,
)

