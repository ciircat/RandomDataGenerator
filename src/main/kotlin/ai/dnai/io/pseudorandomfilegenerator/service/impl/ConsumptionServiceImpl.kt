package ai.dnai.io.pseudorandomfilegenerator.service.impl

import ai.dnai.io.pseudorandomfilegenerator.service.ConsumptionService
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.ConsumptionResult
import org.springframework.stereotype.Service
import java.time.Instant

@Service
open class ConsumptionServiceImpl : ConsumptionService {
    override fun findFirstAndLastConsumptionDate(consumptionResults: List<ConsumptionResult>): List<Instant> {
        var firstConsumption = Instant.MAX
        var lastConsumption = Instant.MIN
        for (i in consumptionResults){
            val dateOfConsumption = Instant.parse(i.consumptionDate)
            if (dateOfConsumption.isBefore(firstConsumption)){
                firstConsumption = dateOfConsumption
            }
            if (dateOfConsumption.isAfter(lastConsumption)){
                lastConsumption = dateOfConsumption
            }
        }

        return listOf(firstConsumption,lastConsumption)

    }
}