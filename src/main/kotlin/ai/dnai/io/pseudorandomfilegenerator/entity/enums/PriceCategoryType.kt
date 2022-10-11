package ai.dnai.io.pseudorandomfilegenerator.entity.enums

enum class PriceCategoryType(val priceLevelFrom: Int, val priceLevelTo: Int) {
    PRICE_CATEGORY_A(priceLevelFrom = 0, priceLevelTo = 100),
    PRICE_CATEGORY_B(priceLevelFrom = 100, priceLevelTo = 1_000),
    PRICE_CATEGORY_C(priceLevelFrom = 1_000, priceLevelTo = 10_000),
    PRICE_CATEGORY_D(priceLevelFrom = 10_000, priceLevelTo = 100_000),
    PRICE_CATEGORY_E(priceLevelFrom = 100_000, priceLevelTo = 1_000_000)
}