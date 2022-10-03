package ai.dnai.io.pseudorandomfilegenerator.service.FileGenerators

import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.ItemResult
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.SupplierResult
import org.springframework.stereotype.Service
import kotlin.random.Random
import kotlin.random.nextInt

@Service
class MustSaleItemGenerator {

    fun assignMustSaleItemsToSuppliers(rng: Random ,itemResultList: List<ItemResult>, supplierResultList: List<SupplierResult>){
        for (i in itemResultList){
            supplierResultList[rng.nextInt(0 until supplierResultList.size)].itemsForSaleList.add(i)
        }
    }
}