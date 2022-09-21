package ai.dnai.io.pseudorandomfilegenerator.service.FileGenerators

import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.CurrencyResult
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.ItemResult
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.PriceItemResult
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.WarehouseStateResult
import org.springframework.stereotype.Service
import java.math.BigDecimal
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.random.nextLong
@Service
class WarehouseStateGenerator {

    fun generateWarehouseStates(priceItems: List<PriceItemResult>,
                                rng: Random,
                                currencies: List<CurrencyResult>): List<WarehouseStateResult>{
        val warehouseStateList = ArrayList<WarehouseStateResult>()
        for (i in 0 until priceItems.size - 1){
            warehouseStateList.add(this.generateWarehouseState(priceItems[i], rng, currencies))
        }
        return warehouseStateList
    }

    private fun generateWarehouseState(priceItemResult: PriceItemResult, rng: Random, currencies: List<CurrencyResult>): WarehouseStateResult {
        val storeQuańtity = rng.nextLong(0L..1000L)
        val minimumQuantity = if (storeQuańtity < 10) {0} else{rng.nextInt(0..10)}
        return WarehouseStateResult(
            itemNumber = priceItemResult.itemNumber,
            units = priceItemResult.units,
            storeQuantity = storeQuańtity.toInt(),
            minimumQuantity = minimumQuantity,
            immediateConsumption = 0,
            totalPrice = BigDecimal(storeQuańtity * priceItemResult.itemPrice.toLong() * this.getConversionRate(priceItemResult.currencyCode, currencies) )

        )
    }

    private fun getConversionRate(currencyCode: String, currencies: List<CurrencyResult>): Int{
        var exchangeRate : Int = -1
        if (currencyCode.equals("CZK")){
            exchangeRate = 1
        }else{
            for (i in currencies){
                if (i.currencyCode.equals(currencyCode)){
                    exchangeRate =  i.exchangeRate.toInt()
                }
            }
        }

        return exchangeRate
    }
}