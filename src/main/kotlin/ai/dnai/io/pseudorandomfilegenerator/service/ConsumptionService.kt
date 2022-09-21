package ai.dnai.io.pseudorandomfilegenerator.service

import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.ConsumptionResult
import org.springframework.stereotype.Service
import java.time.Instant

interface ConsumptionService {
    fun findFirstAndLastConsumptionDate(consumptionResults: List<ConsumptionResult>): List<Instant>
}