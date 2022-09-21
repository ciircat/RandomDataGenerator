package ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity

import java.math.BigDecimal

class WarehouseStateResult constructor(
    val itemNumber: String,
    val units: String,
    val storeQuantity: Int,
    val minimumQuantity: Int,
    val immediateConsumption: Int,
    val totalPrice: BigDecimal
) {

}