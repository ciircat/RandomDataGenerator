package ai.dnai.io.pseudorandomfilegenerator.service.FileGenerators

import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.OrderItemResult
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.OrderResult
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.SupplierResult
import org.springframework.stereotype.Service
import java.time.Instant
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.random.nextLong

@Service
class OrderResultGenerator{
    fun generateOrderResults(suppliers: List<SupplierResult>,
                             ordersFrom: Instant,
                             ordersTo: Instant,
                             numberOfOrders: Int,
                             rng: Random): List<OrderResult> {

        val orderIds = ArrayList<String>()
        val ordersFromInMilliseconds = ordersFrom.toEpochMilli()
        val ordersToInMilliseconds = ordersTo.toEpochMilli()

        val organisationId = suppliers.get(
            rng.nextInt(0 until suppliers.size)
        ).organisationId.toString()
        while (orderIds.size < numberOfOrders) {
            val orderId = rng.nextInt().toString()
            if (!orderIds.contains(orderId)) {
                orderIds.add(orderId)
            }
        }
        val orderResults = ArrayList<OrderResult>()
        for (i in orderIds) {
            val orderDateInMilliseconds = rng.nextLong(ordersFromInMilliseconds..ordersToInMilliseconds)
            val orderDate = Instant.ofEpochMilli(orderDateInMilliseconds)
            val arrivalDate = Instant.ofEpochMilli(orderDateInMilliseconds + rng.nextInt(1..10) * 84000000)
            orderResults.add(
                OrderResult(
                    orderId = i,
                    orderDate = orderDate.toString(),
                    arrivalDate = arrivalDate.toString(),
                    organisationId = organisationId,
                    ArrayList<OrderItemResult>()
                )
            )
        }
        return orderResults
    }

}