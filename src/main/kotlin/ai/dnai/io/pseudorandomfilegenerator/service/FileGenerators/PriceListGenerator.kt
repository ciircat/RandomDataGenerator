package ai.dnai.io.pseudorandomfilegenerator.service.FileGenerators

import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.CurrencyResult
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.ItemResult
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.PriceItemResult
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.SupplierResult
import org.springframework.stereotype.Service
import java.math.BigDecimal
import kotlin.math.absoluteValue
import kotlin.random.Random
import kotlin.random.nextInt
@Service
class PriceListGenerator {
    val priceItemResultList = ArrayList<PriceItemResult>()

    fun generatePriceLists(supplierResults: List<SupplierResult>,
                           itemResults: List<ItemResult>,
                           currencyResults: List<CurrencyResult>,
                           moreThanOnePriceList: Boolean,
                           baseCurrencyCode: String,
                           rng: Random): List<PriceItemResult> {
        if (moreThanOnePriceList) {
            throw NotImplementedError("Not SUpported Yet")
        } else {
            for (i in 0 until currencyResults.size){

                val organisationId = supplierResults.get(
                    rng.nextInt(0 until supplierResults.size)
                ).organisationId

                priceItemResultList.add(this.generatePriceItemResult(rng = rng,
                                                                     currencyCode = currencyResults[i].currencyCode,
                                                                     organisationId = organisationId,
                                                                     itemResult = itemResults[i],
                                                                     forBaseCurrency = false,
                                                                     currencyResults = currencyResults)
                )
            }
            // price item pro base currency
            priceItemResultList.add(this.generatePriceItemResult(rng = rng,
                                                                 currencyCode = baseCurrencyCode,
                                                                 organisationId = supplierResults.get(0).organisationId,
                                                                 itemResult = itemResults[0],
                                                                 forBaseCurrency = true,
                                                                 currencyResults = currencyResults)
            )

            return priceItemResultList
        }
    }

    private fun generatePriceItemResult(forBaseCurrency: Boolean, rng: Random, currencyCode: String, organisationId: String, itemResult: ItemResult, currencyResults: List<CurrencyResult>): PriceItemResult{

        var itemPrice : BigDecimal? = null
        var leadTime : Int? = null
        if (forBaseCurrency){
            itemPrice = BigDecimal(priceItemResultList[0].itemPrice.toLong() / currencyResults[0].exchangeRate.toLong())
            leadTime = priceItemResultList[0].leadTime
        }else{
            itemPrice = BigDecimal(rng.nextInt().absoluteValue)
            leadTime = rng.nextInt(1..10)
        }
        return PriceItemResult(
            itemNumber = itemResult.itemNumber,
            organisationId = organisationId,
            minimumQuantityForGivenPrice = 1,
            itemPrice = itemPrice,
            currencyCode = currencyCode,
            leadTime = leadTime,
            bulk = 0,
            units = itemResult.unit
        )
    }
}