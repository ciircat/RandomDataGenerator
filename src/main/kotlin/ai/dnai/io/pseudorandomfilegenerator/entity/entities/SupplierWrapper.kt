package ai.dnai.io.pseudorandomfilegenerator.entity.entities

import ai.dnai.common.api.entity.Item
import ai.dnai.common.api.entity.Supplier

class SupplierWrapper {
    val supplier: Supplier? = null
    val mustSaleItems: List<Item> = ArrayList()
    val additionalItems: List<Item> = ArrayList()
    //val priceLists: List

}