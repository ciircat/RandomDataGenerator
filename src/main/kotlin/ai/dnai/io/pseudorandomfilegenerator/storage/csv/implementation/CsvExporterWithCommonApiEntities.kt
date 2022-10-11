package ai.dnai.io.pseudorandomfilegenerator.storage.csv.implementation

import ai.dnai.common.api.entity.Currencies
import ai.dnai.common.api.entity.Item
import ai.dnai.common.api.entity.PriceItem
import ai.dnai.common.api.entity.Supplier
import ai.dnai.io.pseudorandomfilegenerator.entity.entities.PriceListWrapper
import ai.dnai.io.pseudorandomfilegenerator.entity.enums.DocumentType
import ai.dnai.io.pseudorandomfilegenerator.storage.FileExporterWithCommonApiEntities
import ai.dnai.io.pseudorandomfilegenerator.utils.DateUtils
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
open class CsvExporterWithCommonApiEntities : FileExporterWithCommonApiEntities{
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

    override fun exportSuppliers(supplierList: List<Supplier>): ByteArray {
        val csv = StringBuilder()
        appendSuppliersFileHeader(csv = csv)
        appendSuppliersHeader(csv = csv)
        supplierList.forEach { supplier -> appendSupplierLine(csv, supplier = supplier) }
        return csv.toString().toByteArray()
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

    private fun appendSupplierLine(csv: StringBuilder, supplier: Supplier){
        csv.append(supplier.organisationId).append(CSV_DELIMITER)
        csv.append(supplier.companyId).append(CSV_DELIMITER)
        csv.append(supplier.companyName).append(CSV_DELIMITER)
        csv.append(supplier.companyDescription).append(CSV_DELIMITER)
        csv.append(supplier.vatId).append(CSV_DELIMITER)
        csv.append(supplier.website)
        csv.append(CSV_LINE_SEPARATOR)
    }




    override fun exportItems(itemList: List<Item>): ByteArray {
        val csv = StringBuilder()
        appendItemsFileHeader(csv = csv)
        appendItemsHeader(csv = csv)
        itemList.forEach { item -> appendItemLine(csv, item) }
        return csv.toString().toByteArray()
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

    private fun appendItemLine(csv: StringBuilder, item: Item){
        csv.append(item.itemNo).append(CSV_DELIMITER)
        csv.append(item.description).append(CSV_DELIMITER)
        csv.append(item.storageCost).append(CSV_DELIMITER)
        //nothing for width
        csv.append(CSV_DELIMITER)
        //nothing for length
        csv.append(CSV_DELIMITER)
        //nothing for height
        csv.append(CSV_DELIMITER)
        csv.append(item.unit).append(CSV_DELIMITER)
        csv.append(item.category).append(CSV_DELIMITER)
        //csv.append(itemResult.abcCategory)
        csv.append(CSV_LINE_SEPARATOR)
    }




    override fun exportCurrencies(currencyList: List<Currencies>): ByteArray {
        val csv = StringBuilder()
        appendCurrencyFileHeader(csv = csv)
        appendCurrenciesHeader(csv = csv)
        currencyList.forEach { currency -> appendCurrencyLine(csv, currency) }
        return csv.toString().toByteArray()
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

    private fun appendCurrencyLine(csv: StringBuilder, currency: Currencies){
        csv.append(currency.currency_code).append(CSV_DELIMITER)
        csv.append(currency.currency_name).append(CSV_DELIMITER)
        csv.append(currency.exchange_rate)
        csv.append(CSV_LINE_SEPARATOR)
    }




    override fun exportPriceLists(priceLists: List<PriceListWrapper>): List<ByteArray> {
        val priceListsAsByteArray = ArrayList<ByteArray>()
        for (priceList in priceLists){
            priceListsAsByteArray.add(
                this.exportPriceList(priceList)
            )
        }
        return priceListsAsByteArray
    }

    private fun exportPriceList(priceList: PriceListWrapper): ByteArray{
        val csv = StringBuilder()
        appendPriceListFileHeader(start = priceList.validFrom.toString(), end = priceList.validTo.toString(), csv = csv)
        appendPriceListsHeader(csv = csv)
        priceList.priceItemList.forEach { priceItem -> appendPriceListLine(csv, priceItem) }
        return csv.toString().toByteArray()
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

    private fun appendPriceListLine(csv: StringBuilder, priceItem: PriceItem){
        csv.append(priceItem.itemNo).append(CSV_DELIMITER)
        csv.append(priceItem.organisationId).append(CSV_DELIMITER)
        csv.append(priceItem.quantity).append(CSV_DELIMITER)
        csv.append(priceItem.itemPrice).append(CSV_DELIMITER)
        csv.append(priceItem.currency).append(CSV_DELIMITER)
        csv.append(priceItem.leadTime).append(CSV_DELIMITER)
        csv.append(priceItem.bulk).append(CSV_DELIMITER)
        csv.append(priceItem.unit)
        csv.append(CSV_LINE_SEPARATOR)

    }


}