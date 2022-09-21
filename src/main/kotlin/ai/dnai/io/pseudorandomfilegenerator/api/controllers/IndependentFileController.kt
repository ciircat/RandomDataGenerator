package ai.dnai.io.pseudorandomfilegenerator.api.controllers

import ai.dnai.io.pseudorandomfilegenerator.api.commands.FilesPropertiesCommand
import ai.dnai.io.pseudorandomfilegenerator.api.exceptions.*
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.io.ByteArrayOutputStream
import ai.dnai.io.pseudorandomfilegenerator.service.ConsumptionService
import ai.dnai.io.pseudorandomfilegenerator.service.FileGenerators.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import java.time.Instant
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

@RestController
class IndependentFileController @Autowired constructor(
    val consumptionService : ConsumptionService,
    val pseudorandomFileGenerationService: PseudorandomFileGenerationService){

    @PostMapping("/files")
    fun getFiles(@ModelAttribute("filesPropertiesCommand") filesPropertiesCommand: FilesPropertiesCommand): ResponseEntity<ByteArray>{
        var initialConsumptionInstant : Instant
        var lastConsumptionInstant : Instant

        var ordersFromInstant : Instant
        var ordersToInstant : Instant

        var priceListValidFromInstant : Instant
        var priceListValidToInstant : Instant
        // validace vstupů
        try {
            initialConsumptionInstant = Instant.parse(filesPropertiesCommand.initialConsumption)
            lastConsumptionInstant = Instant.parse(filesPropertiesCommand.lastConsumption)
        }catch (exception: RuntimeException){
            throw WrongConsumptionException("Unable to parse initial and/or last consumption " + Instant.now().toString())
        }

        try {
            ordersFromInstant = Instant.parse(filesPropertiesCommand.ordersFrom)
            ordersToInstant = Instant.parse(filesPropertiesCommand.ordersTo)
        }catch (exception: RuntimeException){
            throw WrongOrdersFromToRangeException("Unable to parse ordersFrom and/or ordersTo ")
        }

        try {
            priceListValidFromInstant = Instant.parse(filesPropertiesCommand.priceListValidFrom)
            priceListValidToInstant = Instant.parse(filesPropertiesCommand.priceListValidTo)
        }catch (exception: RuntimeException){
            throw WrongPriceListValidDateRange("Please enter correct dates fot priceList")
        }

        if (filesPropertiesCommand.numberOfConsumptions < 1){
            throw WrongNumberOfConsumptionException("Number of consumptions must be at least 1")
        }
        if (filesPropertiesCommand.numberOfOrders < 0){
            throw WrongNumberOfOrdersException("Number of orders must be a positive number")
        }
        if (filesPropertiesCommand.numberOfSuppliers < 1){
            throw WrongNumberOfSuppliersException("Number of suppliers must be at least 1")
        }
        if (filesPropertiesCommand.numberOfCurrenciesAndItems < 1){
            throw WrongNumberOfCurrenciesAndItemsException("Number of Currencies and Items must be at least 1")
        }

        val generatedFiles = this.pseudorandomFileGenerationService.generatePseudorandomFileData(
            initialSeed = filesPropertiesCommand.initialSeed,
            numberOfOrders = filesPropertiesCommand.numberOfOrders,
            numberOfConsumptions = filesPropertiesCommand.numberOfConsumptions,
            numberOfSuppliers = filesPropertiesCommand.numberOfSuppliers,
            numberOfCurrenciesAndItems = filesPropertiesCommand.numberOfCurrenciesAndItems,
            initialConsumptionInstant = initialConsumptionInstant,
            lastConsumptionInstant = lastConsumptionInstant,
            ordersFromInstant = ordersFromInstant,
            ordersToInstant = ordersToInstant,
            priceListValidFromInstant = priceListValidFromInstant,
            priceListValidToInstant = priceListValidToInstant,
            priceListRegion = filesPropertiesCommand.priceListRegion,
            warehouseName = filesPropertiesCommand.warehouseName)

        val baos = ByteArrayOutputStream()
        val zos = ZipOutputStream(baos)
        for (i in 0 until 8){

            val fileName = when(i){
                0 -> "suppliers.csv"
                1 -> "currencies.csv"
                2 -> "items.csv"
                3 -> "suppliersTransportations.csv"
                4-> "priceList.csv"
                5 -> "consumption.csv"
                6-> "warehouseState.csv"
                else -> "orders.csv"
            }
            val entry = ZipEntry(fileName);
            entry.setSize((generatedFiles[i].size).toLong());
            zos.putNextEntry(entry);
            zos.write(generatedFiles[i]);
            zos.closeEntry();
        }
        zos.close();

        val data = baos.toByteArray();
        val headers = HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=randomdata.zip");
        // defining the custom Content-Type
        headers.set(HttpHeaders.CONTENT_TYPE, "application/zip");

        return ResponseEntity(
            data,
            headers,
            HttpStatus.OK
        )
    }

}