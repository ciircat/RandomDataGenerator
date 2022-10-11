package ai.dnai.io.pseudorandomfilegenerator.service.FileGenerators.AdvancedFileGenerators

import ai.dnai.common.api.entity.Currencies
import ai.dnai.common.api.entity.PriceItem
import ai.dnai.io.pseudorandomfilegenerator.entity.entities.ItemWrapper
import ai.dnai.io.pseudorandomfilegenerator.entity.entities.PriceListPeriod
import ai.dnai.io.pseudorandomfilegenerator.entity.entities.PriceListWrapper
import ai.dnai.io.pseudorandomfilegenerator.entity.entities.SupplierWrapper
import org.springframework.stereotype.Service
import kotlin.random.Random
import kotlin.random.nextInt

@Service
class AdvancedPriceListGenerationService {

    fun generatePriceLists(
        rngSeed: Int,
        supplierWrapperList: List<SupplierWrapper>,
        probabilityOfHavingItemInPriceListPeriod: Double,
        currencyList: List<Currencies>,
        leadTimeMean: Int,
        leadTimeStd: Int
    ) {
        val rng = Random(rngSeed)

        this.populatePriceListPeriodsWithItemWrappers(
            rng = Random(rng.nextInt()),
            supplierWrapperList = supplierWrapperList,
            probabilityOfHavingItemInPriceListPeriod = probabilityOfHavingItemInPriceListPeriod
        )

        this.populatePriceListPeriodsWithPriceLists(
            rng = Random(rng.nextInt()),
            supplierWrapperList = supplierWrapperList,
            currencyList = currencyList,
            leadTimeMean = leadTimeMean,
            leadTimeStd = leadTimeStd
        )
    }

    private fun populatePriceListPeriodsWithItemWrappers(
        rng: Random,
        supplierWrapperList: List<SupplierWrapper>,
        probabilityOfHavingItemInPriceListPeriod: Double
    ) {
        for (supplierWrapper in supplierWrapperList) {
            for (priceListPeriod in supplierWrapper.priceListPeriods) {
                for (itemWrapper in supplierWrapper.suppliedItemWrapperSet) {
                    var diceRoll = rng.nextDouble(from = 0.0, until = 1.0)
                    if (diceRoll <= probabilityOfHavingItemInPriceListPeriod) {
                        priceListPeriod.itemsSoldInThisPriceListPeriod.add(itemWrapper)
                    }
                }
            }
        }
    }

    private fun populatePriceListPeriodsWithPriceLists(
        rng: Random,
        supplierWrapperList: List<SupplierWrapper>,
        currencyList: List<Currencies>,
        leadTimeMean: Int,
        leadTimeStd: Int
    ) {
        val currencyListWithBaseCurrency = ArrayList<Currencies>()
        val baseCurrency = Currencies()
        baseCurrency.currency_code = "CZK"
        baseCurrency.currency_name = "base currency"
        baseCurrency.exchange_rate = 1.0
        currencyListWithBaseCurrency.add(baseCurrency)
        for (i in currencyList) {
            currencyListWithBaseCurrency.add(i)
        }

        for (supplierWrapper in supplierWrapperList) {
            for (priceListPeriod in supplierWrapper.priceListPeriods) {
                val numberOfPriceLists = rng.nextInt(1 until currencyListWithBaseCurrency.size)
                for (x in 0 until numberOfPriceLists) {
                    priceListPeriod.priceListWrappers.add(
                        PriceListWrapper(
                            validFrom = priceListPeriod.validFrom,
                            validTo = priceListPeriod.validTo,
                            region = "Ceska republika",
                            priceItemList = this.generatePriceItemList(
                                rng = Random(rng.nextInt()),
                                supplier = supplierWrapper,
                                currency = currencyListWithBaseCurrency[x],
                                itemWrappers = priceListPeriod.itemsSoldInThisPriceListPeriod,
                                leadTimeMean = leadTimeMean,
                                leadTimeStd = leadTimeStd,
                                priceListPeriod = priceListPeriod
                            )
                        )
                    )
                }
            }

        }
    }


    private fun generatePriceItemList(
        rng: Random,
        currency: Currencies,
        supplier: SupplierWrapper,
        itemWrappers: List<ItemWrapper>,
        leadTimeMean: Int,
        leadTimeStd: Int,
        priceListPeriod: PriceListPeriod
    ): List<PriceItem> {
        val javaRandom = java.util.Random()
        val priceItems = ArrayList<PriceItem>()


        for (itemWrapper in itemWrappers) {
            val priceItemSingle = PriceItem()

            priceItemSingle.itemNo = itemWrapper.commonApiItem.itemNo
            priceItemSingle.unit = itemWrapper.commonApiItem.unit
            priceItemSingle.currency = currency.currency_code
            priceItemSingle.bulk = "0"
            priceItemSingle.organisationId = supplier.commonApiSupplier.organisationId
            priceItemSingle.quantity = 1
            priceItemSingle.leadTime = rng.nextInt(from = leadTimeMean - leadTimeStd, until = leadTimeMean + leadTimeStd)
            priceItemSingle.itemPrice = itemWrapper.priceTimeSeries.get(priceListPeriod.timeSeriesPosition).toDouble()

            priceItems.add(priceItemSingle)

            for ((key, value) in itemWrapper.priceDiscountCategory.meanAndStdDiscountAtGivenLevel){
                val priceItemBulk = PriceItem()
                val discount = java.util.Random(rng.nextLong()).nextGaussian(value.first, value.second)

                priceItemBulk.itemNo = itemWrapper.commonApiItem.itemNo
                priceItemBulk.unit = itemWrapper.commonApiItem.unit
                priceItemBulk.currency = currency.currency_code
                priceItemBulk.bulk = "1"
                priceItemBulk.organisationId = supplier.commonApiSupplier.organisationId
                priceItemBulk.quantity = key
                priceItemBulk.leadTime = rng.nextInt(from = leadTimeMean - leadTimeStd, until = leadTimeMean + leadTimeStd)
                priceItemBulk.itemPrice = (1.0 - discount) * itemWrapper.priceTimeSeries.get(priceListPeriod.timeSeriesPosition).toDouble()

                priceItems.add(priceItemBulk)
            }

        }
        return priceItems

    }


}