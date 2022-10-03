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

    fun generateWarehouseStates(items: List<ItemResult>,
                                rng: Random): List<WarehouseStateResult>{
        val warehouseStateList = ArrayList<WarehouseStateResult>()
        for (i in items){
            warehouseStateList.add(
                this.generateWarehouseState(i, rng)
            )

        }
        return warehouseStateList
    }

    private fun generateWarehouseState(item: ItemResult, rng: Random): WarehouseStateResult {
        val storeQuańtity = rng.nextLong(0L..1000L)
        val javaRandom = java.util.Random()
        val minimumQuantity = if (storeQuańtity < 10) {0} else{rng.nextInt(0..10)}
        return WarehouseStateResult(
            itemNumber = item.itemNumber,
            units = item.unit,
            storeQuantity = storeQuańtity.toInt(),
            minimumQuantity = minimumQuantity,
            immediateConsumption = 0,
            totalPrice = BigDecimal(storeQuańtity * javaRandom.nextGaussian(item.basePriceMean,item.basePriceStandardDeviation) )
        )
    }

}