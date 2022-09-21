package ai.dnai.io.pseudorandomfilegenerator.api.commands

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import java.time.Instant

@Component
class FilesPropertiesCommand {
    val presentMomentInSeconds = Instant.now().epochSecond
    val threeMonthsInSeconds = 10518975L
    val twoMonthsInSeconds = 5260000L
    val twelveDaysInSeconds = 1036800L
    val stringInstantTwoMonthsFromNow = Instant.ofEpochSecond(presentMomentInSeconds + twoMonthsInSeconds).toString()

    var initialSeed: Int = 10
    var numberOfSuppliers: Int = 5
    var numberOfCurrenciesAndItems: Int = 5
    var priceListValidFrom: String = Instant.now().toString()
    var priceListValidTo: String = Instant.ofEpochSecond(presentMomentInSeconds + threeMonthsInSeconds).toString()
    var priceListRegion: String = "Ceska republika"
    var numberOfConsumptions: Int = 3
    var initialConsumption: String = Instant.ofEpochSecond(presentMomentInSeconds + twelveDaysInSeconds).toString()
    var lastConsumption: String = stringInstantTwoMonthsFromNow
    var warehouseName: String = "Warehouse_1"
    var numberOfOrders: Int = 3
    var ordersFrom: String = Instant.now().toString()
    var ordersTo: String = stringInstantTwoMonthsFromNow

}