package ai.dnai.io.pseudorandomfilegenerator.service.FileGenerators

import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.CurrencyResult
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.ItemResult
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.OrderItemResult
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.OrderResult
import org.springframework.stereotype.Service
import java.math.BigDecimal
import kotlin.math.absoluteValue
import kotlin.random.Random
import kotlin.random.nextInt

@Service
class OrderItemGenerator {

    fun generateOrderItems(orderResults: List<OrderResult>,
                           itemResults: List<ItemResult>,
                           currencyResults: List<CurrencyResult>,
                           rng: Random): List<OrderItemResult> {
        val orderItemResults = ArrayList<OrderItemResult>()
        for (i in orderResults) {
            val numberOfOrderItemResultsInOrderResult = rng.nextInt(1 until 51)
            for (j in 0..numberOfOrderItemResultsInOrderResult) {
                val currency = currencyResults.get(rng.nextInt(0 until currencyResults.size))
                val currencyExchangeRate = currency.exchangeRate.toInt()
                val currencyCode = currency.currencyCode
                val priceInForeignCurrency = rng.nextInt().absoluteValue
                val item = itemResults.get(rng.nextInt(0 until itemResults.size))
                orderItemResults.add(
                    OrderItemResult(
                        orderId = i.orderId,
                        commonApiId=i.orderId+rng.nextLong().absoluteValue,
                        orderDate = i.orderDate,
                        arrivalDate = i.arrivalDate,
                        itemNumber = item.itemNumber,
                        organisationId = i.organisationId,
                        quantity = rng.nextInt(1 until 31),
                        price = BigDecimal(priceInForeignCurrency),
                        priceBaseCurrency = BigDecimal(priceInForeignCurrency / currencyExchangeRate),
                        currencyCode = currencyCode,
                        unit = item.unit,
                        bulk = 0,
                        fixed = 1
                    )
                )
            }

        }
        return orderItemResults
    }
}