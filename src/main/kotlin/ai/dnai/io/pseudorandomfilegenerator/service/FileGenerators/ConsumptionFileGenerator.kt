package ai.dnai.io.pseudorandomfilegenerator.service.FileGenerators

import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.ConsumptionResult
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.ItemResult
import org.springframework.stereotype.Service
import java.time.Instant
import kotlin.math.absoluteValue
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.random.nextLong

@Service
class ConsumptionFileGenerator {

    fun generateConsumptions(numberOfConsumptions: Int,
                             initialConsumptionDate: Instant,
                             lastConsumptionDate: Instant,
                             itemResults: List<ItemResult>,
                             rng: Random): List<ConsumptionResult> {
        val consumptions = ArrayList<ConsumptionResult>()
        for (i in 0 until numberOfConsumptions) {
            val randIndex = rng.nextInt(0 until itemResults.size)
            val item = itemResults.get(randIndex)
            consumptions.add(generateConsumption(initialConsumptionDate, lastConsumptionDate, item, rng))
        }
        return consumptions
    }

    private fun generateConsumption(initialConsumptionDate: Instant, lastConsumptionDate: Instant, item: ItemResult, rng: Random): ConsumptionResult {
        val initialConsumptionInMilliseconds = initialConsumptionDate.toEpochMilli()
        val lastConsumptionInMilliseconds = lastConsumptionDate.toEpochMilli()
        val consumptionDateInMilliseconds =
            rng.nextLong(initialConsumptionInMilliseconds..lastConsumptionInMilliseconds)
        val consumptionDate = Instant.ofEpochMilli(consumptionDateInMilliseconds)

        return ConsumptionResult(
            itemNumber = item.itemNumber,
            consumptionDate = consumptionDate.toString(),
            projectId = "project" + rng.nextInt(),
            unit = item.unit,
            quantity = rng.nextInt().absoluteValue
        )
    }
}