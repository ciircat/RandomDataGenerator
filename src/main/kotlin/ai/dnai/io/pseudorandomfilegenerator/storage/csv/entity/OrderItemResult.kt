package ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity

import java.math.BigDecimal

class OrderItemResult constructor(
    val orderId: String,
    val commonApiId: String,
    val orderDate: String,
    val arrivalDate: String,
    val itemNumber: String,
    val organisationId: String,
    val quantity: Int,
    val price: BigDecimal,
    val priceBaseCurrency: BigDecimal,
    val currencyCode: String,
    val unit: String,
    val bulk: Int,
    val fixed: Int

) {
}