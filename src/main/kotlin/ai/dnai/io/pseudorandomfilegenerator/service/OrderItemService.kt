package ai.dnai.io.pseudorandomfilegenerator.service

import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.OrderResult
import java.time.Instant

interface OrderItemService {
    fun findFirstAndLastOrderDate(orderResults: List<OrderResult>): List<Instant>
}