package dev.ferrant.core.model

import dev.ferrant.core.model.Coin as UiCoin

sealed class CoinListItem {
    object Skeleton: CoinListItem()
    class Coin(val coin: UiCoin): CoinListItem()
}
