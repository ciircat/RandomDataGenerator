package ai.dnai.io.pseudorandomfilegenerator.storage

import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.*

interface FileExporter {
    fun exportSuppliers(supplierResults: List<SupplierResult>): ByteArray
    fun exportItems(itemResults: List<ItemResult>): ByteArray
    fun exportCurrencies(currencyResults: List<CurrencyResult>): ByteArray
    fun exportPriceLists(priceLists: List<PriceListResult>): List<ByteArray>
    fun exportConsumptions(consumptions: List<ConsumptionResult>, start: String, end: String, warehouseName: String): ByteArray
    fun exportWarehouseStates(warehouseStateResults: List<WarehouseStateResult>, warehouseName: String): ByteArray
    fun exportOrderItems(orderItemResults: List<OrderItemResult>,start: String, end: String, warehouseName: String): ByteArray
    fun exportSupplierTransportations(supplierTransportationResult: List<SupplierTransportationResult>, warehouseName: String): ByteArray
}