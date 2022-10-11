package ai.dnai.io.pseudorandomfilegenerator.entity.entities

import ai.dnai.common.api.entity.Item
import ai.dnai.io.pseudorandomfilegenerator.entity.enums.PriceCategoryType
import ai.dnai.io.pseudorandomfilegenerator.entity.enums.PriceDiscountCategory
import java.math.BigDecimal
import java.time.LocalDate

class ItemWrapper constructor(
    val commonApiItem: Item,
    var basePriceMean: Double,
    var basePriceStandardDeviation: Double,
    var priceChangeCoefficientMean: Double,
    var priceChangeCoefficientStd: Double,
    var lengthOfTimeSeriesInDays: Int,
    var increasingPriceTendency: Boolean,
    var priceDiscountCategory: PriceDiscountCategory
) {
    var priceTimeSeries: ArrayList<Int> = ArrayList()


}