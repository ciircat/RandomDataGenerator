package ai.dnai.io.pseudorandomfilegenerator.service.FileGenerators.AdvancedFileGenerators

import ai.dnai.common.api.entity.Supplier
import ai.dnai.io.pseudorandomfilegenerator.entity.entities.ItemWrapper
import ai.dnai.io.pseudorandomfilegenerator.entity.entities.PriceListPeriod
import ai.dnai.io.pseudorandomfilegenerator.entity.entities.SupplierWrapper
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDate
import kotlin.random.Random

@Service
class AdvancedSupplierGenerationService {
    fun generateSupplierWrapperList(
        rngSeed: Int,
        numberOfSuppliers: Int,
        itemWrapperList: List<ItemWrapper>,
        priceListsValidityInDays: Int,
        numberOfPriceListPeriods: Int
    ): List<SupplierWrapper> {

        var supplierWrapperList = ArrayList<SupplierWrapper>()
        val rng = Random(rngSeed)

        for (i in 0 until numberOfSuppliers) {
            supplierWrapperList.add(
                this.generateSupplierWrapper(
                    rngSeed = rng.nextInt(),
                    priceListsValidityInDays = priceListsValidityInDays,
                    numberOfPriceListPeriods = numberOfPriceListPeriods
                )
            )
        }

        this.addItemsToSupplierWrapper(
            rngSeed = rng.nextInt(),
            itemWrapperList = itemWrapperList,
            supplierWrapperList = supplierWrapperList
        )

        return supplierWrapperList
    }

    private fun generateSupplierWrapper(rngSeed: Int, priceListsValidityInDays: Int, numberOfPriceListPeriods: Int): SupplierWrapper {
        val commonApiSupplier = Supplier()
        val rng = Random(rngSeed)

        commonApiSupplier.organisationId = rng.nextLong().toString()
        commonApiSupplier.companyName = rng.nextInt().toString()
        commonApiSupplier.companyDescription = rng.nextInt().toString()
        commonApiSupplier.companyId = rng.nextLong().toString()
        commonApiSupplier.vatId = rng.nextLong().toString()
        commonApiSupplier.website = "www." + rng.nextInt() + ".com"

        val supplierWrapper = SupplierWrapper(
            commonApiSupplier = commonApiSupplier
        )

        this.generatePriceListPeriodsForGivenSupplierWrapper(supplierWrapper = supplierWrapper, priceListsValidityInDays = priceListsValidityInDays ,numberOfPriceListPeriods = numberOfPriceListPeriods)

        return supplierWrapper
    }

    private fun addItemsToSupplierWrapper(
        rngSeed: Int,
        itemWrapperList: List<ItemWrapper>,
        supplierWrapperList: List<SupplierWrapper>
    ) {
        val rng = Random(rngSeed)

        for (itemWrapper in itemWrapperList) {
            // aby mohlo více dodavatelů mít stejný item
            for (i in 0 until 4) {
                supplierWrapperList.get(
                    rng.nextInt(from = 0, until = supplierWrapperList.size)
                ).addItemWrapper(itemWrapper)
            }
        }
    }

    private fun generatePriceListPeriodsForGivenSupplierWrapper(supplierWrapper: SupplierWrapper, priceListsValidityInDays: Int, numberOfPriceListPeriods: Int){
        var tommorow =  LocalDate.now().plusDays(1)
        var timeSeriesPosition = 0
        for (i in 0 until  numberOfPriceListPeriods){

            supplierWrapper.priceListPeriods.add(
                PriceListPeriod(
                    validFrom = tommorow,
                    validTo = tommorow.plusDays(priceListsValidityInDays.toLong()),
                    timeSeriesPosition = timeSeriesPosition
                )
            )

            tommorow = tommorow.plusDays(priceListsValidityInDays.toLong() + 1)
            timeSeriesPosition += 10
        }
    }


}