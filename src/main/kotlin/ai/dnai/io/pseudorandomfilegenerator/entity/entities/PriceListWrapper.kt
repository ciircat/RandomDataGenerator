package ai.dnai.io.pseudorandomfilegenerator.entity.entities

import ai.dnai.common.api.entity.PriceItem
import java.time.Instant
import java.time.LocalDate

class PriceListWrapper constructor(
    val validFrom: LocalDate,
    val validTo: LocalDate,
    val region: String,
    val priceItemList: List<PriceItem>){
}