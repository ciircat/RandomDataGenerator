package ai.dnai.io.pseudorandomfilegenerator.api.commands

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import java.time.Instant
import java.time.temporal.ChronoUnit

@Component
class FilesPropertiesCommand {
    val presentMoment = Instant.now()
    val stringInstantTwoMonthsFromNow = presentMoment.plus(60, ChronoUnit.DAYS).toString()
    val stringInstantThreeMonthsFromNow = presentMoment.plus(90, ChronoUnit.DAYS).toString()
    val stringInstantTwelveDaysFromNow = presentMoment.plus(12, ChronoUnit.DAYS).toString()

    var initialSeed: Int = 10
    var numberOfSuppliers: Int = 10
    var numberOfCurrencies: Int = 5
    var numberOfItems: Int = 50
    var priceListValidFrom: String = presentMoment.toString()
    var priceListValidTo: String = stringInstantThreeMonthsFromNow
    var priceListRegion: String = "Ceska republika"
    var numberOfConsumptions: Int = 3
    var initialConsumption: String = stringInstantTwelveDaysFromNow
    var lastConsumption: String = stringInstantTwoMonthsFromNow
    var warehouseName: String = "Warehouse_1"
    var numberOfOrders: Int = 3
    var ordersFrom: String = Instant.now().toString()
    var ordersTo: String = stringInstantTwoMonthsFromNow

}