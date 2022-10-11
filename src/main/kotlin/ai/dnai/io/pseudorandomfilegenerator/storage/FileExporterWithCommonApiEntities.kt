package ai.dnai.io.pseudorandomfilegenerator.storage

import ai.dnai.common.api.entity.Currencies
import ai.dnai.common.api.entity.Item
import ai.dnai.common.api.entity.Supplier
import ai.dnai.io.pseudorandomfilegenerator.entity.entities.PriceListWrapper
import ai.dnai.io.pseudorandomfilegenerator.entity.entities.SupplierWrapper
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.*

interface FileExporterWithCommonApiEntities {
    fun exportSuppliers(supplierList: List<Supplier>): ByteArray
    fun exportItems(itemList: List<Item>): ByteArray
    fun exportCurrencies(currencyList: List<Currencies>): ByteArray
    fun exportPriceLists(priceLists: List<PriceListWrapper>): List<ByteArray>
}