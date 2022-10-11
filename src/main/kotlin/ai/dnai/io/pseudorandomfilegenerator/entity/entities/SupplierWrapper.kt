package ai.dnai.io.pseudorandomfilegenerator.entity.entities

import ai.dnai.common.api.entity.Item
import ai.dnai.common.api.entity.Supplier

class SupplierWrapper constructor(
    val commonApiSupplier: Supplier,

){
    var suppliedItemWrapperSet = HashSet<ItemWrapper>()
    var priceListPeriods = ArrayList<PriceListPeriod>()

    fun addItemWrapper(itemWrapper: ItemWrapper){
        this.suppliedItemWrapperSet.add(itemWrapper)
    }


}