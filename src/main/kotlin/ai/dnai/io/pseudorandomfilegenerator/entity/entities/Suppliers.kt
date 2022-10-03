package ai.dnai.io.pseudorandomfilegenerator.entity.entities

import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.ItemResult
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.PriceListResult
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.SupplierResult

class Suppliers {
    var individualSupplierList = ArrayList<SupplierResult>()
    var mustSaleItemList = ArrayList<ItemResult>()
    var additionalItemList = ArrayList<ItemResult>()
    var priceLists = ArrayList<PriceListResult>()
}