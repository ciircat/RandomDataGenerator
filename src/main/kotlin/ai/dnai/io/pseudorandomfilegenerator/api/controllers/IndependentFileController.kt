package ai.dnai.io.pseudorandomfilegenerator.api.controllers

import ai.dnai.io.pseudorandomfilegenerator.api.commands.FilesPropertiesCommand
import ai.dnai.io.pseudorandomfilegenerator.api.exceptions.*
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import ai.dnai.io.pseudorandomfilegenerator.service.FileGenerators.*
import ai.dnai.io.pseudorandomfilegenerator.service.FileServiceImpl.RandomZipFileServiceImpl
import ai.dnai.io.pseudorandomfilegenerator.service.impl.ValidationServiceImpl
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import java.time.Instant

@RestController
class IndependentFileController constructor(
    val pseudorandomFileGenerationService: PseudorandomFileGenerationService,
    val randomZipFileServiceImpl: RandomZipFileServiceImpl,
    val validationServiceImpl: ValidationServiceImpl
) {

    @PostMapping("/files")
    fun getFiles(@ModelAttribute("filesPropertiesCommand") filesPropertiesCommand: FilesPropertiesCommand): ResponseEntity<ByteArray> {

        validationServiceImpl.independentFileControllerInputValidation(
            initialConsumption = filesPropertiesCommand.initialConsumption,
            lastConsumption = filesPropertiesCommand.lastConsumption,
            ordersFrom = filesPropertiesCommand.ordersFrom,
            ordersTo = filesPropertiesCommand.ordersTo,
            priceListValidFrom = filesPropertiesCommand.priceListValidFrom,
            priceListValidTo = filesPropertiesCommand.priceListValidTo,
            numberOfConsumptions = filesPropertiesCommand.numberOfConsumptions,
            numberOfOrders = filesPropertiesCommand.numberOfOrders,
            numberOfSuppliers = filesPropertiesCommand.numberOfSuppliers,
            numberOfCurrencies = filesPropertiesCommand.numberOfCurrencies,
            numberOfItems = filesPropertiesCommand.numberOfItems)

        val initialConsumptionInstant = Instant.parse(filesPropertiesCommand.initialConsumption)
        val lastConsumptionInstant = Instant.parse(filesPropertiesCommand.lastConsumption)

        val ordersFromInstant = Instant.parse(filesPropertiesCommand.ordersFrom)
        val ordersToInstant = Instant.parse(filesPropertiesCommand.ordersTo)

        val priceListValidFromInstant = Instant.parse(filesPropertiesCommand.priceListValidFrom)
        val priceListValidToInstant = Instant.parse(filesPropertiesCommand.priceListValidTo)


        val data = pseudorandomFileGenerationService.generatePseudorandomFileData(
            initialSeed = filesPropertiesCommand.initialSeed,
            numberOfOrders = filesPropertiesCommand.numberOfOrders,
            numberOfConsumptions = filesPropertiesCommand.numberOfConsumptions,
            numberOfSuppliers = filesPropertiesCommand.numberOfSuppliers,
            numberOfCurrencies = filesPropertiesCommand.numberOfCurrencies,
            numberOfItems = filesPropertiesCommand.numberOfItems,
            initialConsumptionInstant = initialConsumptionInstant,
            lastConsumptionInstant = lastConsumptionInstant,
            ordersFromInstant = ordersFromInstant,
            ordersToInstant = ordersToInstant,
            priceListValidFromInstant = priceListValidFromInstant,
            priceListValidToInstant = priceListValidToInstant,
            priceListRegion = filesPropertiesCommand.priceListRegion,
            warehouseName = filesPropertiesCommand.warehouseName
        )
        val zipedData = randomZipFileServiceImpl.zipRandomFiles(data)

        val headers = HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=randomdata.zip");
        // defining the custom Content-Type
        headers.set(HttpHeaders.CONTENT_TYPE, "application/zip");

        return ResponseEntity(
            zipedData,
            headers,
            HttpStatus.OK
        )
    }

}