package ai.dnai.io.pseudorandomfilegenerator.service.FileGenerators

import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.*
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.Instant
import kotlin.math.absoluteValue
import kotlin.random.Random
import kotlin.random.nextInt

@Service
class PriceListGenerator {
    fun generatePriceLists(
        supplierResults: List<SupplierResult>,
        itemResults: List<ItemResult>,
        currencyResults: List<CurrencyResult>,
        baseCurrencyCode: String,
        rng: Random,
        priceListValidFrom: Instant,
        priceListValidTo: Instant,
        priceListRegion: String
    ) {

        val currencyListWithBaseCurrency = ArrayList<CurrencyResult>()
        currencyListWithBaseCurrency.add(
            CurrencyResult(currencyCode = baseCurrencyCode, currencyName = "base currency", exchangeRate = "1")
        )
        for (i in currencyResults) {
            currencyListWithBaseCurrency.add(i)
        }

        for (supplier in supplierResults) {
            val numberOfPriceLists = rng.nextInt(1 until currencyListWithBaseCurrency.size)
            for (x in 0 until numberOfPriceLists) {
                supplier.priceLists.add(
                    PriceListResult(
                        startDate = priceListValidFrom.toString(),
                        endDate = priceListValidTo.toString(),
                        region = priceListRegion,
                        priceItems = this.generatePriceItemResult(
                            rng = rng,
                            supplier = supplier,
                            currency = currencyListWithBaseCurrency[x]
                        )
                    )
                )

            }
        }

    }

    private fun generatePriceItemResult(
        rng: Random,
        currency: CurrencyResult,
        supplier: SupplierResult,
    ): List<PriceItemResult> {
        val javaRandom = java.util.Random()
        val priceItems = ArrayList<PriceItemResult>()


        for (i in supplier.itemsForSaleList) {
            priceItems.add(
                PriceItemResult(
                    itemNumber = i.itemNumber,
                    organisationId = supplier.organisationId,
                    minimumQuantityForGivenPrice = 1,
                    itemPrice = BigDecimal(
                        (javaRandom.nextGaussian(
                            i.basePriceMean,
                            i.basePriceStandardDeviation
                        )) / currency.exchangeRate.toDouble()
                    ),
                    currencyCode = currency.currencyCode,
                    leadTime = rng.nextInt(1..10),
                    bulk = 0,
                    units = i.unit

                )
            )

        }
        return priceItems

    }
}