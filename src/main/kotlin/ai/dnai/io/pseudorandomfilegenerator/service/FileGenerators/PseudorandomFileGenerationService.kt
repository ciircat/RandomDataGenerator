package ai.dnai.io.pseudorandomfilegenerator.service.FileGenerators

import ai.dnai.io.pseudorandomfilegenerator.service.ConsumptionService
import ai.dnai.io.pseudorandomfilegenerator.storage.FileExporter
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.PriceListResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant
import kotlin.random.Random

@Service
class PseudorandomFileGenerationService  @Autowired constructor(
    val independentFilesGenerator: IndependentFilesGenerator,
    val priceListGenerator: PriceListGenerator,
    val warehouseStateGenerator: WarehouseStateGenerator,
    val consumptionFileGenerator: ConsumptionFileGenerator,
    val orderResultGenerator: OrderResultGenerator,
    val orderItemGenerator: OrderItemGenerator,
    val supplierTransportationFileGenerator: SupplierTransportationFileGenerator,
    val fileExporter: FileExporter,
    val consumptionService: ConsumptionService){

    fun generatePseudorandomFileData(initialSeed: Int,
                                     numberOfOrders: Int,
                                     numberOfConsumptions: Int,
                                     numberOfSuppliers: Int,
                                     numberOfCurrenciesAndItems: Int,
                                     initialConsumptionInstant: Instant,
                                     lastConsumptionInstant: Instant,
                                     ordersFromInstant: Instant,
                                     ordersToInstant: Instant,
                                     priceListValidFromInstant: Instant,
                                     priceListValidToInstant: Instant,
                                     priceListRegion: String,
                                     warehouseName: String): ArrayList<ByteArray>{

        val rng = Random(initialSeed)

        val supplierResultList = this.independentFilesGenerator.generateSuppliers(rng, numberOfSuppliers)
        val currencyResultList = this.independentFilesGenerator.generateCurrencies(rng, numberOfCurrenciesAndItems)
        val itemResultList = this.independentFilesGenerator.generateItems(rng, numberOfCurrenciesAndItems)

        val priceItemResultList = priceListGenerator.generatePriceLists(
            supplierResults = supplierResultList,
            currencyResults = currencyResultList,
            itemResults = itemResultList,
            moreThanOnePriceList = false,
            baseCurrencyCode = "CZK",
            rng = rng
        )
        val warehouseStateResults = warehouseStateGenerator.generateWarehouseStates(priceItemResultList, rng, currencyResultList)

        val consumptionResults = consumptionFileGenerator.generateConsumptions(
            numberOfConsumptions = numberOfConsumptions,
            initialConsumptionDate = initialConsumptionInstant,
            lastConsumptionDate = lastConsumptionInstant,
            itemResults = itemResultList,
            rng = rng
        )

        val orderResults = orderResultGenerator.generateOrderResults(supplierResultList, ordersFromInstant, ordersToInstant, numberOfOrders, rng)

        val orderItemResults = orderItemGenerator.generateOrderItems(orderResults,itemResultList,currencyResultList, rng)

        val supplierTransportationResult = supplierTransportationFileGenerator.generateSuppliersTransportations(supplierResultList, currencyResultList, rng)


        val priceList = PriceListResult(priceListValidFromInstant.toString(),priceListValidToInstant.toString(),priceListRegion,priceItemResultList)
        val startAndEnd = consumptionService.findFirstAndLastConsumptionDate(consumptionResults)

        val suppliers = fileExporter.exportSuppliers(supplierResultList)
        val currencies = fileExporter.exportCurrencies(currencyResultList)
        val items = fileExporter.exportItems(itemResultList)
        val priceItems = fileExporter.exportPriceList(priceList)
        val consumptions = fileExporter.exportConsumptions(consumptionResults,startAndEnd[0].toString(),startAndEnd[1].toString(),warehouseName)
        val warehouseStates = fileExporter.exportWarehouseStates(warehouseStateResults, warehouseName)
        val orderItems = fileExporter.exportOrderItems(orderItemResults,ordersFromInstant.toString(), ordersToInstant.toString(), warehouseName)
        val supplierTransportations = fileExporter.exportSupplierTransportations(supplierTransportationResult,warehouseName)

        val files = ArrayList<ByteArray>()
        files.add(suppliers)
        files.add(currencies)
        files.add(items)
        files.add(supplierTransportations)
        files.add(priceItems)
        files.add(consumptions)
        files.add(warehouseStates)
        files.add(orderItems)

        return files
    }
}