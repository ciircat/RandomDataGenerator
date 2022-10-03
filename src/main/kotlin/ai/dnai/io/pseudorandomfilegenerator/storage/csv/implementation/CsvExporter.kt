package ai.dnai.io.pseudorandomfilegenerator.storage.csv.implementation

import ai.dnai.io.pseudorandomfilegenerator.entity.enums.DocumentType
import ai.dnai.io.pseudorandomfilegenerator.storage.FileExporter
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.*
import ai.dnai.io.pseudorandomfilegenerator.utils.DateUtils
import org.springframework.stereotype.Service
import java.time.LocalDate
@Service
open class CsvExporter : FileExporter {
    val dateUtils = DateUtils()
    val CSV_LINE_SEPARATOR = System.lineSeparator()
    val CSV_DELIMITER = ","

    val SUPPLIERS_FILE_HEADER = "file_type,date_created,region"
    val INDIVIDUAL_SUPPLIERS_HEADER = "organisation_id,company_id,company_name,company_description,vat_id,website"

    val ITEMS_FILE_HEADER = "file_type,date_created"
    val INDIVIDUAL_ITEMS_HEADER = "item_no,description,storage_cost,width,length,height,unit,category,abc_category"

    val CURRENCY_FILE_HEADER = "file_type,date_created,region,base_currency_code,base_currency_name"
    val INDIVIDUAL_CURRENCIES_HEADER = "currency_code,currency_name,exchange_rate"

    val PRICE_LIST_FILE_HEADER = "file_type,start,end,region"
    val INDIVIDUAL_PRICE_LIST_HEADER = "item_no,organisation_id,qty,item_price,currency,lead_time,bulk,unit"

    val CONSUMPTION_FILE_HEADER = "file_type,date_created,start,end,warehouse_name"
    val INDIVIDUAL_CONSUMPTIONS_HEADER = "item_no,consumption_date,project_id,quantity,unit"

    val WAREHOUSE_STATE_FILE_HEADER = "file_type,date_created,warehouse_name"
    val INDIVIDUAL_WAREHOUSE_STATES_HEADER = "item_no,store_qty,minimum_qty,immediate_consumption,unit,total_price"

    val ORDERS_FILE_HEADER = "file_type,date_created,start,end,warehouse_name"
    val INDIVIDUAL_ORDERS_HEADER = "id,order_id,order_date,arrival_date,item_no,organisation_id,qty,item_price,currency,item_price_base_currency,unit,force_update"

    val SUPPLIERS_TRANSPORTATIONS_FILE_HEADER = "file_type,date_created,warehouse_name"
    val INDIVIDUAL_SUPPLIER_TRANSPORTATION_HEADER = "organisation_id,transportation_cost,currency"



    override fun exportSuppliers(supplierResults: List<SupplierResult>): ByteArray {
        val csv = StringBuilder()
        appendSuppliersFileHeader(csv = csv)
        appendSuppliersHeader(csv = csv)
        supplierResults.forEach { supplierResult -> appendSupplierLine(csv, supplierResult) }
        return csv.toString().toByteArray()
    }

    override fun exportItems(itemResults: List<ItemResult>): ByteArray {
        val csv = StringBuilder()
        appendItemsFileHeader(csv = csv)
        appendItemsHeader(csv = csv)
        itemResults.forEach { itemResult -> appendItemLine(csv, itemResult) }
        return csv.toString().toByteArray()
    }

    override fun exportCurrencies(currencyResults: List<CurrencyResult>): ByteArray {
        val csv = StringBuilder()
        appendCurrencyFileHeader(csv = csv)
        appendCurrenciesHeader(csv = csv)
        currencyResults.forEach { currencyResult -> appendCurrencyLine(csv, currencyResult) }
        return csv.toString().toByteArray()
    }

    override fun exportPriceLists(priceLists: List<PriceListResult>): List<ByteArray> {
        val priceListsAsByteArray = ArrayList<ByteArray>()
        for (priceList in priceLists){
            priceListsAsByteArray.add(this.exportPriceList(priceList))
        }
        return priceListsAsByteArray
    }

    private fun exportPriceList(priceList: PriceListResult): ByteArray{
        val csv = StringBuilder()
        appendPriceListFileHeader(start = priceList.startDate, end = priceList.endDate, csv = csv)
        appendPriceListsHeader(csv = csv)
        priceList.priceItems.forEach { priceItemResult -> appendPriceListLine(csv, priceItemResult) }
        return csv.toString().toByteArray()
    }


    override fun exportConsumptions(consumptions: List<ConsumptionResult>, start: String, end: String, warehouseName: String): ByteArray {
        val csv = StringBuilder()
        appendConsumptionFileHeader(start = start, end = end, csv = csv, warehouseName = warehouseName)
        appendConsumptionsHeader(csv = csv)
        consumptions.forEach { consumptionResult -> appendConsumptionLine(csv, consumptionResult) }
        return csv.toString().toByteArray()
    }

    override fun exportWarehouseStates(warehouseStateResults: List<WarehouseStateResult>, warehouseName: String): ByteArray {
        val csv = StringBuilder()
        appendWarehouseStateFileHeader(csv, warehouseName)
        appendWarehouseStatesHeader(csv)
        warehouseStateResults.forEach { warehouseStateResult -> appendWarehouseStateLine(csv, warehouseStateResult) }
        return csv.toString().toByteArray()
    }

    override fun exportOrderItems(orderItemResults: List<OrderItemResult>,start: String, end: String, warehouseName: String): ByteArray {
        val csv = StringBuilder()
        appendOrdersFileHeader(csv, start, end, warehouseName)
        appendOrdersHeader(csv)
        orderItemResults.forEach { orderItemResult -> appendOrderLine(csv, orderItemResult) }
        return csv.toString().toByteArray()
    }

    override fun exportSupplierTransportations(supplierTransportationResult: List<SupplierTransportationResult>, warehouseName: String): ByteArray {
        val csv = StringBuilder()
        appendSupplierTransportationFilesHeader(csv, warehouseName)
        appendSupplierTransportationsHeader(csv)
        supplierTransportationResult.forEach { x -> appendSupplierTransportationLine(csv, x)}
        return csv.toString().toByteArray()
    }

    private fun appendSupplierTransportationFilesHeader(csv: StringBuilder, warehouseName: String){
        csv.append("header").append(CSV_LINE_SEPARATOR)
        csv.append(SUPPLIERS_TRANSPORTATIONS_FILE_HEADER).append(CSV_LINE_SEPARATOR)
        csv.append(DocumentType.SUPPLIER_TRANSPORTATION_FILE.type).append(CSV_DELIMITER)
        csv.append(dateUtils.localDateToString(LocalDate.now())).append(CSV_DELIMITER)
        csv.append(warehouseName)

        csv.append(CSV_LINE_SEPARATOR)
        csv.append(CSV_LINE_SEPARATOR)

    }

    private fun appendSupplierTransportationsHeader(csv: StringBuilder){
        csv.append("supplier_transportation").append(CSV_LINE_SEPARATOR)
        csv.append(INDIVIDUAL_SUPPLIER_TRANSPORTATION_HEADER).append(CSV_LINE_SEPARATOR)
    }

    private fun appendSupplierTransportationLine(csv: StringBuilder, supplierTransportationResult: SupplierTransportationResult){
        csv.append(supplierTransportationResult.organisationId).append(CSV_DELIMITER)
        csv.append(supplierTransportationResult.cost).append(CSV_DELIMITER)
        csv.append(supplierTransportationResult.currencyCode)
        csv.append(CSV_LINE_SEPARATOR)
    }

    private fun appendOrdersFileHeader(csv: StringBuilder, start: String, end: String, warehouseName: String){
        csv.append("header").append(CSV_LINE_SEPARATOR)
        csv.append(ORDERS_FILE_HEADER).append(CSV_LINE_SEPARATOR)
        csv.append(DocumentType.ORDER_ITEM_FILE.type).append(CSV_DELIMITER)
        csv.append(dateUtils.localDateToString(LocalDate.now())).append(CSV_DELIMITER)
        csv.append(start).append(CSV_DELIMITER)
        csv.append(end).append(CSV_DELIMITER)
        csv.append(warehouseName)

        csv.append(CSV_LINE_SEPARATOR)
        csv.append(CSV_LINE_SEPARATOR)
    }

    private fun appendOrdersHeader(csv: StringBuilder){
        csv.append("order_item").append(CSV_LINE_SEPARATOR)
        csv.append(INDIVIDUAL_ORDERS_HEADER).append(CSV_LINE_SEPARATOR)
    }

    private fun appendOrderLine(csv: StringBuilder, orderItemResult: OrderItemResult){
        csv.append(orderItemResult.commonApiId).append(CSV_DELIMITER)
        csv.append(orderItemResult.orderId).append(CSV_DELIMITER)
        csv.append(orderItemResult.orderDate).append(CSV_DELIMITER)
        csv.append(orderItemResult.arrivalDate).append(CSV_DELIMITER)
        csv.append(orderItemResult.itemNumber).append(CSV_DELIMITER)
        csv.append(orderItemResult.organisationId).append(CSV_DELIMITER)
        csv.append(orderItemResult.quantity).append(CSV_DELIMITER)
        csv.append(orderItemResult.price).append(CSV_DELIMITER)
        csv.append(orderItemResult.currencyCode).append(CSV_DELIMITER)
        csv.append(orderItemResult.priceBaseCurrency).append(CSV_DELIMITER)
        csv.append(orderItemResult.unit).append(CSV_DELIMITER)
        csv.append(orderItemResult.fixed)
        csv.append(CSV_LINE_SEPARATOR)
    }

    private fun appendWarehouseStateFileHeader(csv: StringBuilder, warehouseName: String){
        csv.append("header").append(CSV_LINE_SEPARATOR)
        csv.append(WAREHOUSE_STATE_FILE_HEADER).append(CSV_LINE_SEPARATOR)
        csv.append(DocumentType.WAREHOUSE_FILE.type).append(CSV_DELIMITER)
        csv.append(dateUtils.localDateToString(LocalDate.now())).append(CSV_DELIMITER)
        csv.append(warehouseName)

        csv.append(CSV_LINE_SEPARATOR)
        csv.append(CSV_LINE_SEPARATOR)
    }

    private fun appendWarehouseStatesHeader(csv: StringBuilder){
        csv.append("state").append(CSV_LINE_SEPARATOR)
        csv.append(INDIVIDUAL_WAREHOUSE_STATES_HEADER).append(CSV_LINE_SEPARATOR)
    }

    private fun appendWarehouseStateLine(csv: StringBuilder, warehouseStateResult: WarehouseStateResult){
        csv.append(warehouseStateResult.itemNumber).append(CSV_DELIMITER)
        csv.append(warehouseStateResult.storeQuantity).append(CSV_DELIMITER)
        csv.append(warehouseStateResult.minimumQuantity).append(CSV_DELIMITER)
        csv.append(warehouseStateResult.immediateConsumption).append(CSV_DELIMITER)
        csv.append(warehouseStateResult.units).append(CSV_DELIMITER)
        csv.append(warehouseStateResult.totalPrice)
        csv.append(CSV_LINE_SEPARATOR)
    }

    private fun appendConsumptionFileHeader(csv: StringBuilder, start: String, end: String, warehouseName: String){
        csv.append("header").append(CSV_LINE_SEPARATOR)
        csv.append(CONSUMPTION_FILE_HEADER).append(CSV_LINE_SEPARATOR)
        csv.append(DocumentType.CONSUMPTION_FILE.type).append(CSV_DELIMITER)
        csv.append(dateUtils.localDateToString(LocalDate.now())).append(CSV_DELIMITER)
        csv.append(start).append(CSV_DELIMITER)
        csv.append(end).append(CSV_DELIMITER)
        csv.append(warehouseName)

        csv.append(CSV_LINE_SEPARATOR)
        csv.append(CSV_LINE_SEPARATOR)
    }

    private fun appendConsumptionsHeader(csv: StringBuilder){
        csv.append("consumption_item").append(CSV_LINE_SEPARATOR)
        csv.append(INDIVIDUAL_CONSUMPTIONS_HEADER).append(CSV_LINE_SEPARATOR)
    }

    private fun appendConsumptionLine(csv: StringBuilder, consumptionResult: ConsumptionResult){
        csv.append(consumptionResult.itemNumber).append(CSV_DELIMITER)
        csv.append(consumptionResult.consumptionDate).append(CSV_DELIMITER)
        csv.append(consumptionResult.projectId).append(CSV_DELIMITER)
        csv.append(consumptionResult.quantity).append(CSV_DELIMITER)
        csv.append(consumptionResult.unit)
        csv.append(CSV_LINE_SEPARATOR)
    }



    private fun appendPriceListFileHeader(csv: StringBuilder, start: String, end: String){
        csv.append("header").append(CSV_LINE_SEPARATOR)
        csv.append(PRICE_LIST_FILE_HEADER).append(CSV_LINE_SEPARATOR)
        csv.append(DocumentType.PRICE_LIST_FILE.type).append(CSV_DELIMITER)
        csv.append(start).append(CSV_DELIMITER)
        csv.append(end).append(CSV_DELIMITER)
        csv.append("Ceska republika")

        csv.append(CSV_LINE_SEPARATOR)
        csv.append(CSV_LINE_SEPARATOR)
    }

    private fun appendPriceListsHeader(csv: StringBuilder){
        csv.append("price_item").append(CSV_LINE_SEPARATOR)
        csv.append(INDIVIDUAL_PRICE_LIST_HEADER).append(CSV_LINE_SEPARATOR)
    }

    private fun appendPriceListLine(csv: StringBuilder, priceItem: PriceItemResult){
        csv.append(priceItem.itemNumber).append(CSV_DELIMITER)
        csv.append(priceItem.organisationId).append(CSV_DELIMITER)
        csv.append(priceItem.minimumQuantityForGivenPrice).append(CSV_DELIMITER)
        csv.append(priceItem.itemPrice).append(CSV_DELIMITER)
        csv.append(priceItem.currencyCode).append(CSV_DELIMITER)
        csv.append(priceItem.leadTime).append(CSV_DELIMITER)
        csv.append(priceItem.bulk).append(CSV_DELIMITER)
        csv.append(priceItem.units)
        csv.append(CSV_LINE_SEPARATOR)

    }

    private fun appendCurrencyFileHeader(csv: StringBuilder){
        csv.append("header").append(CSV_LINE_SEPARATOR)
        csv.append(CURRENCY_FILE_HEADER).append(CSV_LINE_SEPARATOR)
        csv.append(DocumentType.CURRENCY_FILE.type).append(CSV_DELIMITER)
        csv.append(dateUtils.localDateToString(LocalDate.now())).append(CSV_DELIMITER)
        csv.append("Ceska rupublika").append(CSV_DELIMITER)
        csv.append("CZK").append(CSV_DELIMITER)
        csv.append("Base currency CZK")
        csv.append(CSV_LINE_SEPARATOR)
        csv.append(CSV_LINE_SEPARATOR)
    }

    private fun appendCurrenciesHeader(csv: StringBuilder){
        csv.append("currency").append(CSV_LINE_SEPARATOR)
        csv.append(INDIVIDUAL_CURRENCIES_HEADER).append(CSV_LINE_SEPARATOR)
    }

    private fun appendCurrencyLine(csv: StringBuilder, currencyResult: CurrencyResult){
        csv.append(currencyResult.currencyCode).append(CSV_DELIMITER)
        csv.append(currencyResult.currencyName).append(CSV_DELIMITER)
        csv.append(currencyResult.exchangeRate)
        csv.append(CSV_LINE_SEPARATOR)
    }

    private fun appendItemsFileHeader(csv: StringBuilder){
        csv.append("header").append(CSV_LINE_SEPARATOR)
        csv.append(ITEMS_FILE_HEADER).append(CSV_LINE_SEPARATOR)
        csv.append(DocumentType.ITEM_FILE.type).append(CSV_DELIMITER)
        csv.append(dateUtils.localDateToString(LocalDate.now()))
        csv.append(CSV_LINE_SEPARATOR)
        csv.append(CSV_LINE_SEPARATOR)
    }

    private fun appendItemsHeader(csv: StringBuilder){
        csv.append("item").append(CSV_LINE_SEPARATOR)
        csv.append(INDIVIDUAL_ITEMS_HEADER).append(CSV_LINE_SEPARATOR)
    }

    private fun appendItemLine(csv: StringBuilder, itemResult: ItemResult){
        csv.append(itemResult.itemNumber).append(CSV_DELIMITER)
        csv.append(itemResult.description).append(CSV_DELIMITER)
        csv.append(itemResult.storageCost).append(CSV_DELIMITER)
        //nothing for width
        csv.append(CSV_DELIMITER)
        //nothing for length
        csv.append(CSV_DELIMITER)
        //nothing for height
        csv.append(CSV_DELIMITER)
        csv.append(itemResult.unit).append(CSV_DELIMITER)
        csv.append(itemResult.category).append(CSV_DELIMITER)
        //csv.append(itemResult.abcCategory)
        csv.append(CSV_LINE_SEPARATOR)
    }

    private fun appendSuppliersFileHeader(csv: StringBuilder){
        csv.append("header").append(CSV_LINE_SEPARATOR)
        csv.append(SUPPLIERS_FILE_HEADER).append(CSV_LINE_SEPARATOR)
        csv.append(DocumentType.SUPPLIER_FILE.type).append(CSV_DELIMITER)
        csv.append(dateUtils.localDateToString(LocalDate.now())).append(CSV_DELIMITER)
        csv.append("Ceska republika")
        csv.append(CSV_LINE_SEPARATOR)
        csv.append(CSV_LINE_SEPARATOR)
    }

    private fun appendSuppliersHeader(csv: StringBuilder){
        csv.append("supplier").append(CSV_LINE_SEPARATOR)
        csv.append(INDIVIDUAL_SUPPLIERS_HEADER).append(CSV_LINE_SEPARATOR)
    }

    private fun appendSupplierLine(csv: StringBuilder, supplierResult: SupplierResult){
        csv.append(supplierResult.organisationId).append(CSV_DELIMITER)
        csv.append(supplierResult.companyId).append(CSV_DELIMITER)
        csv.append(supplierResult.companyName).append(CSV_DELIMITER)
        csv.append(supplierResult.companyDescription).append(CSV_DELIMITER)
        csv.append(supplierResult.vatId).append(CSV_DELIMITER)
        csv.append(supplierResult.webside)
        csv.append(CSV_LINE_SEPARATOR)
    }
}