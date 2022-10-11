package ai.dnai.io.pseudorandomfilegenerator.service.FileGenerators.AdvancedFileGenerators

import ai.dnai.common.api.entity.Item
import ai.dnai.io.pseudorandomfilegenerator.entity.entities.ItemWrapper
import ai.dnai.io.pseudorandomfilegenerator.entity.enums.PriceCategoryType
import ai.dnai.io.pseudorandomfilegenerator.entity.enums.PriceDiscountCategory
import org.springframework.stereotype.Service
import kotlin.math.absoluteValue
import kotlin.random.Random

@Service
class AdvancedItemGenerationService {

    fun generateItemWrappers(
        rngSeed: Int,
        numberOfItems: Int,
        basePriceStdAsPercentageOfMean: Double,
        changeCoefMeanIncreasing: Double,
        changeCoefStdIncreasing: Double,
        changeCoefMeanDecreasing: Double,
        changeCoefStdDecreasing: Double,
        partOfItemsIn0to100: Double,
        partOfItemsIn100to1_000: Double,
        partOfItemsIn1_000to10_000: Double,
        partOfItemsIn10_000to100_000: Double,
        priceListsValidityInDays: Int,
        numberOfPriceListPeriods: Int,

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
    ): List<ItemWrapper> {
        val itemWrapperList = ArrayList<ItemWrapper>()

        val rng = Random(rngSeed)

        for (i in 0 until numberOfItems) {
            itemWrapperList.add(
                this.generateItemWrapper(
                    rngSeed = rng.nextInt(),
                    basePriceStdAsPercentageOfMean = basePriceStdAsPercentageOfMean,
                    changeCoefMeanIncreasing = changeCoefMeanIncreasing,
                    changeCoefMeanDecreasing = changeCoefMeanDecreasing,
                    changeCoefStdIncreasing = changeCoefStdIncreasing,
                    changeCoefStdDecreasing = changeCoefStdDecreasing,
                    probabilityOf0To100 = partOfItemsIn0to100,
                    probabilityOf100To1_000 = partOfItemsIn100to1_000,
                    probabilityOf1_000To10_000 = partOfItemsIn1_000to10_000,
                    probabilityOf10_000To100_000 = partOfItemsIn10_000to100_000,
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
            )
        }
        return itemWrapperList
    }

    private fun generateItemWrapper(
        rngSeed: Int,
        basePriceStdAsPercentageOfMean: Double,
        changeCoefMeanIncreasing: Double,
        changeCoefStdIncreasing: Double,
        changeCoefMeanDecreasing: Double,
        changeCoefStdDecreasing: Double,
        probabilityOf0To100: Double,
        probabilityOf100To1_000: Double,
        probabilityOf1_000To10_000: Double,
        probabilityOf10_000To100_000: Double,
        priceListsValidityInDays: Int,
        numberOfPriceListPeriods: Int,

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
    ): ItemWrapper {
        val rng = Random(rngSeed)
        val item = Item().apply {
            abcCategory = generateAbcCategory(rng.nextInt())
            category = "categoryNumber${rng.nextInt().absoluteValue}"
            description = "${rng.nextInt()}"
            height = rng.nextInt().absoluteValue
            itemNo = rng.nextInt().toString()
            length = rng.nextInt().absoluteValue
            storageCost = rng.nextInt().absoluteValue
            unit = rng.nextInt().toString()
            width = rng.nextInt().absoluteValue
        }


        val basePriceAndCategoryType = this.getBaseItemPriceMeanInCorrectRange(
            rng = rng,
            probabilityOf0To100 = probabilityOf0To100,
            probabilityOf100To1_000 = probabilityOf100To1_000,
            probabilityOf1_000To10_000 = probabilityOf1_000To10_000,
            probabilityOf10_000To100_000 = probabilityOf10_000To100_000,
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
        val basePriceMean = basePriceAndCategoryType.first

        val isIncreasing = this.isIncreasing(rng.nextInt())

        val priceChangeCoefficientMean = if (isIncreasing) {
            changeCoefMeanIncreasing
        } else {
            changeCoefMeanDecreasing
        }

        val priceChangeCoefficientStd = if (isIncreasing) {
            changeCoefStdIncreasing
        } else {
            changeCoefStdDecreasing
        }

        val itemWrapper = ItemWrapper(
            commonApiItem = item,
            basePriceMean = basePriceMean,
            basePriceStandardDeviation = basePriceMean * basePriceStdAsPercentageOfMean,
            priceChangeCoefficientMean = priceChangeCoefficientMean,
            priceChangeCoefficientStd = priceChangeCoefficientStd,
            lengthOfTimeSeriesInDays = priceListsValidityInDays * numberOfPriceListPeriods,
            increasingPriceTendency = isIncreasing,
            priceDiscountCategory = basePriceAndCategoryType.second
        )

        this.generateItemWrapperTimeSeries(itemWrapper)

        return itemWrapper
    }

    private fun getBaseItemPriceMeanInCorrectRange(
        rng: Random,
        probabilityOf0To100: Double,
        probabilityOf100To1_000: Double,
        probabilityOf1_000To10_000: Double,
        probabilityOf10_000To100_000: Double,
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
    ): Pair<Double, PriceDiscountCategory> {

        val x = rng.nextDouble(0.0, 1.0)

        if (x < probabilityOf0To100) {
            val priceDiscountCategory = PriceDiscountCategory(PriceCategoryType.PRICE_CATEGORY_A)
            priceDiscountCategory.meanAndStdDiscountAtGivenLevel.put(firstItemCountLvl0to100, Pair(firstItemCountDiscountMeanLvl0to100, firstItemCountDiscountStdLvl0to100))
            priceDiscountCategory.meanAndStdDiscountAtGivenLevel.put(secondItemCountLvl0to100, Pair(secondItemCountDiscountMeanLvl0to100, secondItemCountDiscountStdLvl0to100))
            priceDiscountCategory.meanAndStdDiscountAtGivenLevel.put(thirdItemCountLvl0to100, Pair(thirdItemCountDiscountMeanLvl0to100, thirdItemCountDiscountStdLvl0to100))
            return Pair(rng.nextDouble(from = 0.0, until = 100.0), priceDiscountCategory)

        } else if (x >= probabilityOf0To100 && x < (probabilityOf0To100 + probabilityOf100To1_000)) {
            val priceDiscountCategory = PriceDiscountCategory(PriceCategoryType.PRICE_CATEGORY_B)
            priceDiscountCategory.meanAndStdDiscountAtGivenLevel.put(firstItemCountLvl100to1000, Pair(firstItemCountDiscountMeanLvl100to1000, firstItemCountDiscountStdLvl100to1000))
            priceDiscountCategory.meanAndStdDiscountAtGivenLevel.put(secondItemCountLvl100to1000, Pair(secondItemCountDiscountMeanLvl100to1000, secondItemCountDiscountStdLvl100to1000))
            priceDiscountCategory.meanAndStdDiscountAtGivenLevel.put(thirdItemCountLvl100to1000, Pair(thirdItemCountDiscountMeanLvl100to1000, thirdItemCountDiscountStdLvl100to1000))

            return Pair(rng.nextDouble(from = 100.0, until = 1_000.0), priceDiscountCategory)

        } else if (x >= (probabilityOf0To100 + probabilityOf100To1_000) && x < (probabilityOf0To100 + probabilityOf100To1_000 + probabilityOf1_000To10_000)) {
            val priceDiscountCategory = PriceDiscountCategory(PriceCategoryType.PRICE_CATEGORY_C)
            priceDiscountCategory.meanAndStdDiscountAtGivenLevel.put(firstItemCountLvl1000to10000, Pair(firstItemCountDiscountMeanLvl1000to10000, firstItemCountDiscountStdLvl1000to10000))
            priceDiscountCategory.meanAndStdDiscountAtGivenLevel.put(secondItemCountLvl1000to10000, Pair(secondItemCountDiscountMeanLvl1000to10000, secondItemCountDiscountStdLvl1000to10000))
            priceDiscountCategory.meanAndStdDiscountAtGivenLevel.put(thirdItemCountLvl1000to10000, Pair(thirdItemCountDiscountMeanLvl1000to10000, thirdItemCountDiscountStdLvl1000to10000))

            return Pair(rng.nextDouble(from = 1_000.0, until = 10_000.0), priceDiscountCategory)

        } else if (x >= (probabilityOf0To100 + probabilityOf100To1_000 + probabilityOf1_000To10_000) && x < (probabilityOf0To100 + probabilityOf100To1_000 + probabilityOf1_000To10_000 + probabilityOf10_000To100_000)) {

            val priceDiscountCategory = PriceDiscountCategory(PriceCategoryType.PRICE_CATEGORY_D)
            priceDiscountCategory.meanAndStdDiscountAtGivenLevel.put(firstItemCountLvl10000to100000, Pair(firstItemCountDiscountMeanLvl10000to100000, firstItemCountDiscountStdLvl10000to100000))
            priceDiscountCategory.meanAndStdDiscountAtGivenLevel.put(secondItemCountLvl10000to100000, Pair(secondItemCountDiscountMeanLvl10000to100000, secondItemCountDiscountStdLvl10000to100000))
            priceDiscountCategory.meanAndStdDiscountAtGivenLevel.put(thirdItemCountLvl10000to100000, Pair(thirdItemCountDiscountMeanLvl10000to100000, thirdItemCountDiscountStdLvl10000to100000))

            return Pair(rng.nextDouble(from = 10_000.0, until = 100_000.0), priceDiscountCategory)

        } else {
            val priceDiscountCategory = PriceDiscountCategory(PriceCategoryType.PRICE_CATEGORY_E)
            priceDiscountCategory.meanAndStdDiscountAtGivenLevel.put(firstItemCountLvl100000to1000000, Pair(firstItemCountDiscountMeanLvl100000to1000000, firstItemCountDiscountStdLvl100000to1000000))
            priceDiscountCategory.meanAndStdDiscountAtGivenLevel.put(secondItemCountLvl100000to1000000, Pair(secondItemCountDiscountMeanLvl100000to1000000, secondItemCountDiscountStdLvl100000to1000000))
            priceDiscountCategory.meanAndStdDiscountAtGivenLevel.put(thirdItemCountLvl100000to1000000, Pair(thirdItemCountDiscountMeanLvl100000to1000000, thirdItemCountDiscountStdLvl100000to1000000))

            return Pair(rng.nextDouble(from = 100_000.0, until = 1_000_000.0), priceDiscountCategory)
        }

    }

    private fun generateAbcCategory(rngSeed: Int): String {
        val randomNumber = Random(rngSeed).nextInt(from = 0, until = 9)
        return if (randomNumber < 3) {
            "A"
        } else if (randomNumber < 6) {
            "B"
        } else {
            "C"
        }
    }

    private fun isIncreasing(rngSeed: Int): Boolean {
        return Random(rngSeed).nextDouble(from = 0.0, until = 1.0) < 0.5

    }

    private fun generateItemWrapperTimeSeries(itemWrapper: ItemWrapper) {
        val dailyMean = itemWrapper.priceChangeCoefficientMean / 365
        val dailyStd = itemWrapper.priceChangeCoefficientStd / 365
        val javaRandom = java.util.Random()

        var price = javaRandom.nextGaussian(itemWrapper.basePriceMean, itemWrapper.basePriceStandardDeviation)
        itemWrapper.priceTimeSeries.add(price.toInt())

        for (i in 1 until itemWrapper.lengthOfTimeSeriesInDays) {
            if (itemWrapper.increasingPriceTendency) {
                price = price * (1 + javaRandom.nextGaussian(dailyMean, dailyStd))
            } else {
                price = price * (1 - javaRandom.nextGaussian(dailyMean, dailyStd))
            }
            itemWrapper.priceTimeSeries.add(price.toInt())
        }
    }
}