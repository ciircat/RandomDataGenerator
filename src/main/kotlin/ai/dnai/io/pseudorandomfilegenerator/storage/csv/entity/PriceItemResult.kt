package ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity

import java.math.BigDecimal

class PriceItemResult constructor(
    val itemNumber: String,
    val organisationId: String,
    val minimumQuantityForGivenPrice: Int = 1,
    val itemPrice: BigDecimal,
    val currencyCode: String,
    val leadTime: Int,
    val bulk: Int,
    val units: String
) {
}