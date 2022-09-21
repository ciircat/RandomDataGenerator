package ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity

import java.time.Instant
import java.time.ZonedDateTime

class ConsumptionResult constructor(
    val itemNumber: String,
    val consumptionDate: String,
    val projectId: String,
    val unit: String,
    val quantity: Int
) {

}