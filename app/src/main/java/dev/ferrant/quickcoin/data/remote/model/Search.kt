package dev.ferrant.quickcoin.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class CoinsSearch (
    @Json(name = "coins")
    val coins: List<Coin>,
)

data class Coin (
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
