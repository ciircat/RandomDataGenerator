package ai.dnai.io.pseudorandomfilegenerator.service.FileGenerators

import ai.dnai.io.pseudorandomfilegenerator.entity.enums.DocumentType
import ai.dnai.io.pseudorandomfilegenerator.service.ConsumptionService
import ai.dnai.io.pseudorandomfilegenerator.storage.FileExporter
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.PriceListResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.ListResourceBundle
import kotlin.random.Random

@Service
class PseudorandomFileGenerationService @Autowired constructor(
    val independentFilesGenerator: IndependentFilesGenerator,
    val priceListGenerator: PriceListGenerator,
    val warehouseStateGenerator: WarehouseStateGenerator,
    val consumptionFileGenerator: ConsumptionFileGenerator,
    val orderResultGenerator: OrderResultGenerator,
    val orderItemGenerator: OrderItemGenerator,
    val supplierTransportationFileGenerator: SupplierTransportationFileGenerator,
    val fileExporter: FileExporter,
    val consumptionService: ConsumptionService,
    val mustSaleItemGenerator: MustSaleItemGenerator
) {

    fun generatePseudorandomFileData(
        initialSeed: Int,
        numberOfOrders: Int,
        numberOfConsumptions: Int,
        numberOfSuppliers: Int,
        numberOfCurrencies: Int,
        numberOfItems: Int,
        initialConsumptionInstant: Instant,
        lastConsumptionInstant: Instant,
        ordersFromInstant: Instant,
        ordersToInstant: Instant,
        priceListValidFromInstant: Instant,
        priceListValidToInstant: Instant,
        priceListRegion: String,
        warehouseName: String
    ): Map<DocumentType, List<ByteArray> > {

        val rng = Random(initialSeed)

        val supplierResultList = this.independentFilesGenerator.generateSupplierResultList(rng, numberOfSuppliers)
        val currencyResultList = this.independentFilesGenerator.generateCurrencies(rng, numberOfCurrencies)
        val itemResultList = this.independentFilesGenerator.generateItems(rng, numberOfItems)

        this.mustSaleItemGenerator.assignMustSaleItemsToSuppliers(
            rng = rng,
            itemResultList = itemResultList,
            supplierResultList = supplierResultList
        )

        val priceItemResultList = priceListGenerator.generatePriceLists(
            supplierResults = supplierResultList,
            currencyResults = currencyResultList,
            itemResults = itemResultList,
            baseCurrencyCode = "CZK",
            rng = rng,
            priceListRegion = priceListRegion,
            priceListValidFrom = priceListValidFromInstant,
            priceListValidTo = priceListValidToInstant
        )

        val warehouseStateResults =
            warehouseStateGenerator.generateWarehouseStates(itemResultList, rng)

        val consumptionResults = consumptionFileGenerator.generateConsumptions(
            numberOfConsumptions = numberOfConsumptions,
            initialConsumptionDate = initialConsumptionInstant,
            lastConsumptionDate = lastConsumptionInstant,
            itemResults = itemResultList,
            rng = rng
        )

        val orderResults = orderResultGenerator.generateOrderResults(
            supplierResultList,
            ordersFromInstant,
            ordersToInstant,
            numberOfOrders,
            rng
        )

        val orderItemResults =
            orderItemGenerator.generateOrderItems(orderResults, itemResultList, currencyResultList, rng)

        val supplierTransportationResult = supplierTransportationFileGenerator.generateSuppliersTransportations(
            supplierResultList,
            currencyResultList,
            rng
        )

        val startAndEnd = consumptionService.findFirstAndLastConsumptionDate(consumptionResults)

        val suppliers = fileExporter.exportSuppliers(supplierResultList)
        val currencies = fileExporter.exportCurrencies(currencyResultList)
        val items = fileExporter.exportItems(itemResultList)

        val priceLists = ArrayList<PriceListResult>()

        for (supplier in supplierResultList){
            for (supplierPriceList in supplier.priceLists){
                priceLists.add(supplierPriceList)
            }
        }

        val priceListsAsByteArray = fileExporter.exportPriceLists(priceLists)

        val consumptions = fileExporter.exportConsumptions(
            consumptionResults,
            startAndEnd[0].toString(),
            startAndEnd[1].toString(),
            warehouseName
        )
        val warehouseStates = fileExporter.exportWarehouseStates(warehouseStateResults, warehouseName)
        val orderItems = fileExporter.exportOrderItems(
            orderItemResults,
            ordersFromInstant.toString(),
            ordersToInstant.toString(),
            warehouseName
        )
        val supplierTransportations =
            fileExporter.exportSupplierTransportations(supplierTransportationResult, warehouseName)

        val files = HashMap<DocumentType, List<ByteArray> >()
        files.put(DocumentType.SUPPLIER_FILE, listOf(suppliers) )
        files.put(DocumentType.CURRENCY_FILE, listOf(currencies) )
        files.put(DocumentType.ITEM_FILE, listOf(items))
        files.put(DocumentType.SUPPLIER_TRANSPORTATION_FILE, listOf(supplierTransportations))
        files.put(DocumentType.PRICE_LIST_FILE, priceListsAsByteArray)
        files.put(DocumentType.CONSUMPTION_FILE, listOf(consumptions) )
        files.put(DocumentType.WAREHOUSE_FILE, listOf(warehouseStates) )
        files.put(DocumentType.ORDER_ITEM_FILE, listOf(orderItems))

        return files
    }
}