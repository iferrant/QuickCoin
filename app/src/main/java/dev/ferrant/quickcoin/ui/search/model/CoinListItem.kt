package dev.ferrant.quickcoin.ui.search.model

import dev.ferrant.quickcoin.domain.model.Coin as UiCoin


sealed class CoinListItem {
    object Skeleton: CoinListItem()
    class Coin(val coin: UiCoin): CoinListItem()
}
