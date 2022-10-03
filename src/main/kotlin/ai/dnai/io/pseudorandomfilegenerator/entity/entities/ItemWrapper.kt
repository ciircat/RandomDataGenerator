package ai.dnai.io.pseudorandomfilegenerator.entity.entities

import ai.dnai.common.api.entity.Item

class ItemWrapper constructor(
    val commonApiItem: Item,
    var basePriceMean: Double,
    var basePriceStandardDeviation: Double
) {



}