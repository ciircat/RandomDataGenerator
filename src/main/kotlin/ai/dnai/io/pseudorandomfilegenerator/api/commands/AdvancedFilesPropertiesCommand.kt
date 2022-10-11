package ai.dnai.io.pseudorandomfilegenerator.api.commands

import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.ChronoUnit

@Component
class AdvancedFilesPropertiesCommand {
    val presentMoment = Instant.now()
    val stringInstantTwoMonthsFromNow = presentMoment.plus(60, ChronoUnit.DAYS).toString()
    val stringInstantThreeMonthsFromNow = presentMoment.plus(90, ChronoUnit.DAYS).toString()
    val stringInstantTwelveDaysFromNow = presentMoment.plus(12, ChronoUnit.DAYS).toString()

    var initialSeed: Int = 10
    var numberOfItems: Int = 50
    var basePriceStdAsPercentageOfMean: Double = 0.1

    var priceChangeCoefficientMeanPerYearForItemsWithIncreasingPriceTendency: Double = 0.02
    var priceChangeCoefficientStdPerYearForItemsWithIncreasingPriceTendency: Double = 0.005

    var priceChangeCoefficientMeanPerYearForItemsWithDecreasingPriceTendency: Double = 0.02
    var priceChangeCoefficientStdPerYearForItemsWithDecreasingPriceTendency: Double = 0.005

    var partOfItemsIn0to100: Double = 0.2
    var firstItemCountLvl0to100: Int = 1_000
    var firstItemCountDiscountMeanLvl0to100: Double = 0.03
    var firstItemCountDiscountStdLvl0to100: Double = 0.005
    var secondItemCountLvl0to100: Int = 10_000
    var secondItemCountDiscountMeanLvl0to100: Double = 0.05
    var secondItemCountDiscountStdLvl0to100: Double = 0.005
    var thirdItemCountLvl0to100: Int = 50_000
    var thirdItemCountDiscountMeanLvl0to100: Double = 0.1
    var thirdItemCountDiscountStdLvl0to100: Double = 0.005


    var partOfItemsIn100to1000: Double = 0.4
    var firstItemCountLvl100to1000: Int = 100
    var firstItemCountDiscountMeanLvl100to1000: Double = 0.03
    var firstItemCountDiscountStdLvl100to1000: Double = 0.005
    var secondItemCountLvl100to1000: Int = 1_000
    var secondItemCountDiscountMeanLvl100to1000: Double = 0.05
    var secondItemCountDiscountStdLvl100to1000: Double = 0.005
    var thirdItemCountLvl100to1000: Int = 5_000
    var thirdItemCountDiscountMeanLvl100to1000: Double = 0.1
    var thirdItemCountDiscountStdLvl100to1000: Double = 0.005


    var partOfItemsIn1000to10000: Double = 0.3
    var firstItemCountLvl1000to10000: Int = 10
    var firstItemCountDiscountMeanLvl1000to10000: Double = 0.03
    var firstItemCountDiscountStdLvl1000to10000: Double = 0.005
    var secondItemCountLvl1000to10000: Int = 100
    var secondItemCountDiscountMeanLvl1000to10000: Double = 0.05
    var secondItemCountDiscountStdLvl1000to10000: Double = 0.005
    var thirdItemCountLvl1000to10000: Int = 5_000
    var thirdItemCountDiscountMeanLvl1000to10000: Double = 0.1
    var thirdItemCountDiscountStdLvl1000to10000: Double = 0.005


    var partOfItemsIn10000to100000: Double = 0.1
    var firstItemCountLvl10000to100000: Int = 10
    var firstItemCountDiscountMeanLvl10000to100000: Double = 0.03
    var firstItemCountDiscountStdLvl10000to100000: Double = 0.005
    var secondItemCountLvl10000to100000: Int = 100
    var secondItemCountDiscountMeanLvl10000to100000: Double = 0.05
    var secondItemCountDiscountStdLvl10000to100000: Double = 0.005
    var thirdItemCountLvl10000to100000: Int = 5_000
    var thirdItemCountDiscountMeanLvl10000to100000: Double = 0.1
    var thirdItemCountDiscountStdLvl10000to100000: Double = 0.005



    var partOfItemsIn100000to1000000: Double = 0.0
    var firstItemCountLvl100000to1000000: Int = 10
    var firstItemCountDiscountMeanLvl100000to1000000: Double = 0.03
    var firstItemCountDiscountStdLvl100000to1000000: Double = 0.005
    var secondItemCountLvl100000to1000000: Int = 100
    var secondItemCountDiscountMeanLvl100000to1000000: Double = 0.05
    var secondItemCountDiscountStdLvl100000to1000000: Double = 0.005
    var thirdItemCountLvl100000to1000000: Int = 5_000
    var thirdItemCountDiscountMeanLvl100000to1000000: Double = 0.1
    var thirdItemCountDiscountStdLvl100000to1000000: Double = 0.005

    var numberOfCurrencies: Int = 5
    var numberOfSuppliers: Int = 10
    var priceListValidityInDays: Int = 10
    var numberOfPriceListPeriods: Int = 36
    var probabilityOfHavingItemInPriceListPeriod: Double = 0.9
    var leadTimeMean: Int = 6
    var leadTimeStd: Int = 2




}