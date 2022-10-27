package dev.ferrant.core.model

data class Coin(
    val id: String,
    val large: String? = null,
    val marketCapRank: Int? = null,
    val name: String,
    val symbol: String,
    val thumb: String? = null,
)
