package ai.dnai.io.pseudorandomfilegenerator.service.FileGenerators.AdvancedFileGenerators

import ai.dnai.common.api.entity.Item
import ai.dnai.common.api.entity.Supplier
import ai.dnai.io.pseudorandomfilegenerator.entity.entities.PriceListWrapper
import ai.dnai.io.pseudorandomfilegenerator.entity.enums.DocumentType
import ai.dnai.io.pseudorandomfilegenerator.service.FileGenerators.IndependentFilesGenerator
import ai.dnai.io.pseudorandomfilegenerator.storage.FileExporterWithCommonApiEntities
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant
import kotlin.random.Random

@Service
class AdvancedPseudorandomFileGenerationService @Autowired constructor(
    val advancedItemGenerationService: AdvancedItemGenerationService,
    val advancedCurrencyGenerationService: AdvancedCurrencyGenerationService,
    val advancedSupplierGenerationService: AdvancedSupplierGenerationService,
    val advancedPriceListGenerationService: AdvancedPriceListGenerationService,
    val fileExporterWithCommonApiEntities: FileExporterWithCommonApiEntities
) {

    fun generateAdvancedPseudorandomFileData(
        initialSeed: Int,
        numberOfItems: Int,
        basePriceStdAsPercentageOfMean: Double,
        changeCoefMeanIncreasing: Double,
        changeCoefMeanDecreasing: Double,
        changeCoefStdIncreasing: Double,
        changeCoefStdDecreasing: Double,
        partOfItemsIn0to100: Double,
        partOfItemsIn100to1_000: Double,
        partOfItemsIn1_000to10_000: Double,
        partOfItemsIn10_000to100_000: Double,
        numberOfCurrencies: Int,
        numberOfSuppliers: Int,
        priceListsValidityInDays: Int,
        numberOfPriceListPeriods: Int,
        probabilityOfHavingItemInPriceListPeriod: Double,
        leadTimeMean: Int,
        leadTimeStd: Int,
        firstItemCountLvl0to100: Int,
        firstItemCountDiscountMeanLvl0to100: Double,
        firstItemCountDiscountStdLvl0to100: Double,
        secondItemCountLvl0to100: Int,
        secondItemCountDiscountMeanLvl0to100: Double,
        secondItemCountDiscountStdLvl0to100: Double,
        thirdItemCountLvl0to100: Int,
        thirdItemCountDiscountMeanLvl0to100: Double,
        thirdItemCountDiscountStdLvl0to100: Double,


        firstItemCountLvl100to1000: Int,
        firstItemCountDiscountMeanLvl100to1000: Double,
        firstItemCountDiscountStdLvl100to1000: Double,
        secondItemCountLvl100to1000: Int,
        secondItemCountDiscountMeanLvl100to1000: Double,
        secondItemCountDiscountStdLvl100to1000: Double,
        thirdItemCountLvl100to1000: Int,
        thirdItemCountDiscountMeanLvl100to1000: Double,
        thirdItemCountDiscountStdLvl100to1000: Double,


        firstItemCountLvl1000to10000: Int,
        firstItemCountDiscountMeanLvl1000to10000: Double,
        firstItemCountDiscountStdLvl1000to10000: Double,
        secondItemCountLvl1000to10000: Int,
        secondItemCountDiscountMeanLvl1000to10000: Double,
        secondItemCountDiscountStdLvl1000to10000: Double,
        thirdItemCountLvl1000to10000: Int,
        thirdItemCountDiscountMeanLvl1000to10000: Double,
        thirdItemCountDiscountStdLvl1000to10000: Double,


        firstItemCountLvl10000to100000: Int,
        firstItemCountDiscountMeanLvl10000to100000: Double,
        firstItemCountDiscountStdLvl10000to100000: Double,
        secondItemCountLvl10000to100000: Int,
        secondItemCountDiscountMeanLvl10000to100000: Double,
        secondItemCountDiscountStdLvl10000to100000: Double,
        thirdItemCountLvl10000to100000: Int,
        thirdItemCountDiscountMeanLvl10000to100000: Double,
        thirdItemCountDiscountStdLvl10000to100000: Double,


        firstItemCountLvl100000to1000000: Int,
        firstItemCountDiscountMeanLvl100000to1000000: Double,
        firstItemCountDiscountStdLvl100000to1000000: Double,
        secondItemCountLvl100000to1000000: Int,
        secondItemCountDiscountMeanLvl100000to1000000: Double,
        secondItemCountDiscountStdLvl100000to1000000: Double,
        thirdItemCountLvl100000to1000000: Int,
        thirdItemCountDiscountMeanLvl100000to1000000: Double,
        thirdItemCountDiscountStdLvl100000to1000000: Double,

    ): Map<DocumentType, List<ByteArray>>? {
        val rng = Random(initialSeed)

        val itemWrapperList = this.advancedItemGenerationService.generateItemWrappers(
            rngSeed = rng.nextInt(),
            numberOfItems = numberOfItems,
            basePriceStdAsPercentageOfMean = basePriceStdAsPercentageOfMean,
            changeCoefMeanIncreasing = changeCoefMeanIncreasing,
            changeCoefMeanDecreasing = changeCoefMeanDecreasing,
            changeCoefStdIncreasing = changeCoefStdIncreasing,
            changeCoefStdDecreasing = changeCoefStdDecreasing,
            partOfItemsIn0to100 = partOfItemsIn0to100,
            partOfItemsIn100to1_000 = partOfItemsIn100to1_000,
            partOfItemsIn1_000to10_000 = partOfItemsIn1_000to10_000,
            partOfItemsIn10_000to100_000 = partOfItemsIn10_000to100_000,
            priceListsValidityInDays = priceListsValidityInDays,
            numberOfPriceListPeriods = numberOfPriceListPeriods,

            firstItemCountLvl0to100= firstItemCountLvl0to100,
            firstItemCountDiscountMeanLvl0to100 = firstItemCountDiscountMeanLvl0to100,
            firstItemCountDiscountStdLvl0to100 = firstItemCountDiscountStdLvl0to100,
            secondItemCountLvl0to100 = secondItemCountLvl0to100,
            secondItemCountDiscountMeanLvl0to100 = secondItemCountDiscountMeanLvl0to100,
            secondItemCountDiscountStdLvl0to100 = secondItemCountDiscountStdLvl0to100,
            thirdItemCountLvl0to100 = thirdItemCountLvl0to100,
            thirdItemCountDiscountMeanLvl0to100 = thirdItemCountDiscountMeanLvl0to100,
            thirdItemCountDiscountStdLvl0to100 = thirdItemCountDiscountStdLvl0to100,


            firstItemCountLvl100to1000 = firstItemCountLvl100to1000 ,
            firstItemCountDiscountMeanLvl100to1000 = firstItemCountDiscountMeanLvl100to1000,
            firstItemCountDiscountStdLvl100to1000 = firstItemCountDiscountStdLvl100to1000,
            secondItemCountLvl100to1000 = secondItemCountLvl100to1000,
            secondItemCountDiscountMeanLvl100to1000 = secondItemCountDiscountMeanLvl100to1000,
            secondItemCountDiscountStdLvl100to1000 = secondItemCountDiscountStdLvl100to1000,
            thirdItemCountLvl100to1000 = thirdItemCountLvl100to1000,
            thirdItemCountDiscountMeanLvl100to1000 = thirdItemCountDiscountMeanLvl100to1000,
            thirdItemCountDiscountStdLvl100to1000 = thirdItemCountDiscountStdLvl100to1000,


            firstItemCountLvl1000to10000 = firstItemCountLvl1000to10000,
            firstItemCountDiscountMeanLvl1000to10000 = firstItemCountDiscountMeanLvl1000to10000,
            firstItemCountDiscountStdLvl1000to10000 = firstItemCountDiscountStdLvl1000to10000,
            secondItemCountLvl1000to10000 = secondItemCountLvl1000to10000,
            secondItemCountDiscountMeanLvl1000to10000 = secondItemCountDiscountMeanLvl1000to10000,
            secondItemCountDiscountStdLvl1000to10000 = secondItemCountDiscountStdLvl1000to10000,
            thirdItemCountLvl1000to10000 = thirdItemCountLvl1000to10000,
            thirdItemCountDiscountMeanLvl1000to10000 = thirdItemCountDiscountMeanLvl1000to10000,
            thirdItemCountDiscountStdLvl1000to10000 = thirdItemCountDiscountStdLvl1000to10000,


            firstItemCountLvl10000to100000= firstItemCountLvl10000to100000,
            firstItemCountDiscountMeanLvl10000to100000= firstItemCountDiscountMeanLvl10000to100000,
            firstItemCountDiscountStdLvl10000to100000= firstItemCountDiscountStdLvl10000to100000,
            secondItemCountLvl10000to100000 = secondItemCountLvl10000to100000,
            secondItemCountDiscountMeanLvl10000to100000 = secondItemCountDiscountMeanLvl10000to100000,
            secondItemCountDiscountStdLvl10000to100000 = secondItemCountDiscountStdLvl10000to100000,
            thirdItemCountLvl10000to100000 = thirdItemCountLvl10000to100000,
            thirdItemCountDiscountMeanLvl10000to100000 = thirdItemCountDiscountMeanLvl10000to100000,
            thirdItemCountDiscountStdLvl10000to100000 = thirdItemCountDiscountStdLvl10000to100000,


            firstItemCountLvl100000to1000000 = firstItemCountLvl100000to1000000,
            firstItemCountDiscountMeanLvl100000to1000000 = firstItemCountDiscountMeanLvl100000to1000000 ,
            firstItemCountDiscountStdLvl100000to1000000 = firstItemCountDiscountStdLvl100000to1000000 ,
            secondItemCountLvl100000to1000000 = secondItemCountLvl100000to1000000 ,
            secondItemCountDiscountMeanLvl100000to1000000 = secondItemCountDiscountMeanLvl100000to1000000 ,
            secondItemCountDiscountStdLvl100000to1000000 = secondItemCountDiscountStdLvl100000to1000000 ,
            thirdItemCountLvl100000to1000000 = thirdItemCountLvl100000to1000000 ,
            thirdItemCountDiscountMeanLvl100000to1000000 = thirdItemCountDiscountMeanLvl100000to1000000 ,
            thirdItemCountDiscountStdLvl100000to1000000 = thirdItemCountDiscountStdLvl100000to1000000 ,
        )

        val currencyList = this.advancedCurrencyGenerationService.generateCurrencyList(
            rngSeed = rng.nextInt(),
            numberOfCurrencies = numberOfCurrencies
        )

        val supplierWrapperList = this.advancedSupplierGenerationService.generateSupplierWrapperList(
            rngSeed = rng.nextInt(),
            numberOfSuppliers = numberOfSuppliers,
            itemWrapperList = itemWrapperList,
            priceListsValidityInDays = priceListsValidityInDays,
            numberOfPriceListPeriods = numberOfPriceListPeriods
        )

        this.advancedPriceListGenerationService.generatePriceLists(
            rngSeed = rng.nextInt(),
            supplierWrapperList = supplierWrapperList,
            probabilityOfHavingItemInPriceListPeriod = probabilityOfHavingItemInPriceListPeriod,
            currencyList = currencyList,
            leadTimeMean = leadTimeMean,
            leadTimeStd = leadTimeStd
        )

        val priceListWrappers = ArrayList<PriceListWrapper>()
        val supplierList = ArrayList<Supplier>()
        for (supplierWrapper in supplierWrapperList){
            supplierList.add(supplierWrapper.commonApiSupplier)
            for (priceListPeriod in supplierWrapper.priceListPeriods){
                for (priceListWrapper in priceListPeriod.priceListWrappers){
                    priceListWrappers.add(priceListWrapper)
                }
            }
        }

        val itemList = ArrayList<Item>()
        for (itemWrapper in itemWrapperList){
            itemList.add(itemWrapper.commonApiItem)
        }


        val suppliers = this.fileExporterWithCommonApiEntities.exportSuppliers(supplierList)
        val currencies = this.fileExporterWithCommonApiEntities.exportCurrencies(currencyList)
        val items = this.fileExporterWithCommonApiEntities.exportItems(itemList)

        val priceListsAsByteArray = this.fileExporterWithCommonApiEntities.exportPriceLists(priceListWrappers)

        val files = HashMap<DocumentType, List<ByteArray> >()
        files.put(DocumentType.SUPPLIER_FILE, listOf(suppliers) )
        files.put(DocumentType.CURRENCY_FILE, listOf(currencies) )
        files.put(DocumentType.ITEM_FILE, listOf(items))
        files.put(DocumentType.PRICE_LIST_FILE, priceListsAsByteArray)


        return files
    }
}