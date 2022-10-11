package ai.dnai.io.pseudorandomfilegenerator.entity.enums

class PriceDiscountCategory constructor(
    val priceItemCategoryType: PriceCategoryType
) {
    val meanAndStdDiscountAtGivenLevel = HashMap<Int, Pair<Double,Double>>()
}