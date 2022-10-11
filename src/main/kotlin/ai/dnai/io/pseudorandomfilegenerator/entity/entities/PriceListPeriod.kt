package ai.dnai.io.pseudorandomfilegenerator.entity.entities

import java.time.Instant
import java.time.LocalDate

class PriceListPeriod constructor(val validFrom: LocalDate, val validTo: LocalDate, val timeSeriesPosition: Int) {
    var priceListWrappers = ArrayList<PriceListWrapper>()
    var itemsSoldInThisPriceListPeriod = ArrayList<ItemWrapper>()
}