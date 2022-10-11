package ai.dnai.io.pseudorandomfilegenerator.service.impl

import ai.dnai.io.pseudorandomfilegenerator.api.exceptions.*
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class ValidationServiceImpl {
    fun advancedDataGenerationFileControllerInputValidation(){

    }
    fun independentFileControllerInputValidation(
        initialConsumption: String,
        lastConsumption: String,
        ordersFrom: String,
        ordersTo: String,
        priceListValidFrom: String,
        priceListValidTo: String,
        numberOfConsumptions: Int,
        numberOfOrders: Int,
        numberOfSuppliers: Int,
        numberOfCurrencies: Int,
        numberOfItems: Int

    ) {
        var initialConsumptionInstant: Instant
        var lastConsumptionInstant: Instant

        var ordersFromInstant: Instant
        var ordersToInstant: Instant

        var priceListValidFromInstant: Instant
        var priceListValidToInstant: Instant
        // validace vstupů
        try {
            initialConsumptionInstant = Instant.parse(initialConsumption)
            lastConsumptionInstant = Instant.parse(lastConsumption)
        } catch (exception: RuntimeException) {
            throw WrongConsumptionException(
                "Unable to parse initial and/or last consumption"
            )
        }

        try {
            ordersFromInstant = Instant.parse(ordersFrom)
            ordersToInstant = Instant.parse(ordersTo)
        } catch (exception: RuntimeException) {
            throw WrongOrdersFromToRangeException("Unable to parse ordersFrom and/or ordersTo ")
        }

        try {
            priceListValidFromInstant = Instant.parse(priceListValidFrom)
            priceListValidToInstant = Instant.parse(priceListValidTo)
        } catch (exception: RuntimeException) {
            throw WrongPriceListValidDateRange("Please enter correct dates fot priceList")
        }

        if(initialConsumptionInstant.isAfter(lastConsumptionInstant)){
            throw WrongConsumptionException("initial consumption date must be before last consumption date.")
        }

        if (ordersFromInstant.isAfter(ordersToInstant)){
            throw WrongOrdersFromToRangeException("Orders from parameter must be before orders to parameter")
        }

        if (priceListValidFromInstant.isAfter(priceListValidToInstant)){
            throw WrongPriceListValidDateRange("Price list valid from must be before price list valid to")
        }

        if (numberOfConsumptions < 1) {
            throw WrongNumberOfConsumptionException("Number of consumptions must be at least 1")
        }
        if (numberOfOrders < 0) {
            throw WrongNumberOfOrdersException("Number of orders must be a positive number")
        }
        if (numberOfSuppliers < 1) {
            throw WrongNumberOfSuppliersException("Number of suppliers must be at least 1")
        }
        if (numberOfCurrencies < 1) {
            throw WrongNumberOfCurrenciesException("Number of Currencies must be at least 1")
        }
        if (numberOfItems < 1){
            throw WrongNumberOfItemsException("Number of Items must be at least 1")
        }
    }
}