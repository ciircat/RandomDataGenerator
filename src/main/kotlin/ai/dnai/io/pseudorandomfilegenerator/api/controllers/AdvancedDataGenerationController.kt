package ai.dnai.io.pseudorandomfilegenerator.api.controllers

import ai.dnai.io.pseudorandomfilegenerator.api.commands.AdvancedFilesPropertiesCommand
import ai.dnai.io.pseudorandomfilegenerator.api.exceptions.AdvancedFileGenerationExceptions.WrongBasePriceStdAsPercentageOfMeanException
import ai.dnai.io.pseudorandomfilegenerator.api.exceptions.AdvancedFileGenerationExceptions.WrongPriceChangeCoefficientMeanPerYearException
import ai.dnai.io.pseudorandomfilegenerator.api.exceptions.WrongNumberOfItemsException
import ai.dnai.io.pseudorandomfilegenerator.service.FileGenerators.AdvancedFileGenerators.AdvancedPseudorandomFileGenerationService
import ai.dnai.io.pseudorandomfilegenerator.service.FileServiceImpl.RandomZipFileServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class AdvancedDataGenerationController @Autowired constructor(
    val advancedPseudorandomFileGenerationService: AdvancedPseudorandomFileGenerationService,
    val randomZipFileServiceImpl: RandomZipFileServiceImpl
) {


    @PostMapping("/generateAdvancedData")
    fun getAdvancedFiles(@ModelAttribute("filesPropertiesCommand") advancedFilesPropertiesCommand: AdvancedFilesPropertiesCommand): ResponseEntity<ByteArray> {
        val initialSeed = advancedFilesPropertiesCommand.initialSeed
        val numberOfCurrencies = advancedFilesPropertiesCommand.numberOfCurrencies
        val numberOfItems = advancedFilesPropertiesCommand.numberOfItems
        val numberOfSuppliers = advancedFilesPropertiesCommand.numberOfSuppliers
        val priceListsValidityInDays = advancedFilesPropertiesCommand.priceListValidityInDays
        val numberOfPriceListPeriods = advancedFilesPropertiesCommand.numberOfPriceListPeriods
        val probabilityOfHavingItemInPriceListPeriod = advancedFilesPropertiesCommand.probabilityOfHavingItemInPriceListPeriod
        val leadTimeMean = advancedFilesPropertiesCommand.leadTimeMean
        val leadTimeStd = advancedFilesPropertiesCommand.leadTimeStd



        val basePriceStdAsPercentageOfMean = advancedFilesPropertiesCommand.basePriceStdAsPercentageOfMean
        val changeCoefMeanIncreasing =
            advancedFilesPropertiesCommand.priceChangeCoefficientMeanPerYearForItemsWithIncreasingPriceTendency
        val changeCoefMeanDecreasing =
            advancedFilesPropertiesCommand.priceChangeCoefficientMeanPerYearForItemsWithDecreasingPriceTendency
        val changeCoefStdIncreasing =
            advancedFilesPropertiesCommand.priceChangeCoefficientStdPerYearForItemsWithIncreasingPriceTendency
        val changeCoefStdDecreasing =
            advancedFilesPropertiesCommand.priceChangeCoefficientStdPerYearForItemsWithDecreasingPriceTendency

        val partOfItemsIn0to100 = advancedFilesPropertiesCommand.partOfItemsIn0to100
        val partOfItemsIn100to1_000 = advancedFilesPropertiesCommand.partOfItemsIn100to1000
        val partOfItemsIn1_000to10_000 = advancedFilesPropertiesCommand.partOfItemsIn1000to10000
        val partOfItemsIn10_000to100_000 = advancedFilesPropertiesCommand.partOfItemsIn10000to100000
        val partOfItemsIn100_000to1_000_000 = advancedFilesPropertiesCommand.partOfItemsIn100000to1000000

        if (numberOfItems < 1) {
            throw WrongNumberOfItemsException("Number of items must be larger than 0")
        }
        if (basePriceStdAsPercentageOfMean <= 0) {
            throw WrongBasePriceStdAsPercentageOfMeanException("Base price std as percentage of mean must be larger than 0.")
        }
        if (changeCoefMeanIncreasing < 0 || changeCoefMeanDecreasing < 0) {
            throw WrongPriceChangeCoefficientMeanPerYearException("Price change coefficient means must be non-negative")
        }
        if (changeCoefStdIncreasing < 0 || changeCoefStdDecreasing < 0) {
            throw WrongPriceChangeCoefficientMeanPerYearException("Price change coefficient stds must be non-negative")
        }/*
        if ( (partOfItemsIn0to100 + partOfItemsIn100to1_000 + partOfItemsIn1_000to10_000 + partOfItemsIn10_000to100_000 + partOfItemsIn100_000to1_000_000) != 1.0){
            throw WrongItemPriceDistributionException("The parts of items in different range must sum up to 1. Given ${partOfItemsIn0to100} + ${partOfItemsIn100to1_000} + ${partOfItemsIn1_000to10_000} + ${partOfItemsIn10_000to100_000} + ${partOfItemsIn100_000to1_000_000} = ${partOfItemsIn0to100 + partOfItemsIn100to1_000 + partOfItemsIn1_000to10_000 + partOfItemsIn10_000to100_000 + partOfItemsIn100_000to1_000_000}")
        }*/
        val data = advancedPseudorandomFileGenerationService.generateAdvancedPseudorandomFileData(
            initialSeed = initialSeed,
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
            numberOfCurrencies = numberOfCurrencies,
            numberOfSuppliers = numberOfSuppliers,
            priceListsValidityInDays = priceListsValidityInDays,
            numberOfPriceListPeriods = numberOfPriceListPeriods,
            probabilityOfHavingItemInPriceListPeriod = probabilityOfHavingItemInPriceListPeriod,
            leadTimeMean = leadTimeMean,
            leadTimeStd = leadTimeStd,
            firstItemCountLvl0to100= advancedFilesPropertiesCommand.firstItemCountLvl0to100,
            firstItemCountDiscountMeanLvl0to100 = advancedFilesPropertiesCommand.firstItemCountDiscountMeanLvl0to100,
            firstItemCountDiscountStdLvl0to100 = advancedFilesPropertiesCommand.firstItemCountDiscountStdLvl0to100,
            secondItemCountLvl0to100 = advancedFilesPropertiesCommand.secondItemCountLvl0to100,
            secondItemCountDiscountMeanLvl0to100 = advancedFilesPropertiesCommand.secondItemCountDiscountMeanLvl0to100,
            secondItemCountDiscountStdLvl0to100 = advancedFilesPropertiesCommand.secondItemCountDiscountStdLvl0to100,
            thirdItemCountLvl0to100 = advancedFilesPropertiesCommand.thirdItemCountLvl0to100,
            thirdItemCountDiscountMeanLvl0to100 = advancedFilesPropertiesCommand.thirdItemCountDiscountMeanLvl0to100,
            thirdItemCountDiscountStdLvl0to100 = advancedFilesPropertiesCommand.thirdItemCountDiscountStdLvl0to100,


            firstItemCountLvl100to1000 = advancedFilesPropertiesCommand.firstItemCountLvl100to1000 ,
            firstItemCountDiscountMeanLvl100to1000 = advancedFilesPropertiesCommand.firstItemCountDiscountMeanLvl100to1000,
            firstItemCountDiscountStdLvl100to1000 = advancedFilesPropertiesCommand.firstItemCountDiscountStdLvl100to1000,
            secondItemCountLvl100to1000 = advancedFilesPropertiesCommand.secondItemCountLvl100to1000,
            secondItemCountDiscountMeanLvl100to1000 = advancedFilesPropertiesCommand.secondItemCountDiscountMeanLvl100to1000,
            secondItemCountDiscountStdLvl100to1000 = advancedFilesPropertiesCommand.secondItemCountDiscountStdLvl100to1000,
            thirdItemCountLvl100to1000 = advancedFilesPropertiesCommand.thirdItemCountLvl100to1000,
            thirdItemCountDiscountMeanLvl100to1000 = advancedFilesPropertiesCommand.thirdItemCountDiscountMeanLvl100to1000,
            thirdItemCountDiscountStdLvl100to1000 = advancedFilesPropertiesCommand.thirdItemCountDiscountStdLvl100to1000,


            firstItemCountLvl1000to10000 = advancedFilesPropertiesCommand.firstItemCountLvl1000to10000,
            firstItemCountDiscountMeanLvl1000to10000 = advancedFilesPropertiesCommand.firstItemCountDiscountMeanLvl1000to10000,
            firstItemCountDiscountStdLvl1000to10000 = advancedFilesPropertiesCommand.firstItemCountDiscountStdLvl1000to10000,
            secondItemCountLvl1000to10000 = advancedFilesPropertiesCommand.secondItemCountLvl1000to10000,
            secondItemCountDiscountMeanLvl1000to10000 = advancedFilesPropertiesCommand.secondItemCountDiscountMeanLvl1000to10000,
            secondItemCountDiscountStdLvl1000to10000 = advancedFilesPropertiesCommand.secondItemCountDiscountStdLvl1000to10000,
            thirdItemCountLvl1000to10000 = advancedFilesPropertiesCommand.thirdItemCountLvl1000to10000,
            thirdItemCountDiscountMeanLvl1000to10000 = advancedFilesPropertiesCommand.thirdItemCountDiscountMeanLvl1000to10000,
            thirdItemCountDiscountStdLvl1000to10000 = advancedFilesPropertiesCommand.thirdItemCountDiscountStdLvl1000to10000,


            firstItemCountLvl10000to100000= advancedFilesPropertiesCommand.firstItemCountLvl10000to100000,
            firstItemCountDiscountMeanLvl10000to100000= advancedFilesPropertiesCommand.firstItemCountDiscountMeanLvl10000to100000,
            firstItemCountDiscountStdLvl10000to100000= advancedFilesPropertiesCommand.firstItemCountDiscountStdLvl10000to100000,
            secondItemCountLvl10000to100000 = advancedFilesPropertiesCommand.secondItemCountLvl10000to100000,
            secondItemCountDiscountMeanLvl10000to100000 = advancedFilesPropertiesCommand.secondItemCountDiscountMeanLvl10000to100000,
            secondItemCountDiscountStdLvl10000to100000 = advancedFilesPropertiesCommand.secondItemCountDiscountStdLvl10000to100000,
            thirdItemCountLvl10000to100000 = advancedFilesPropertiesCommand.thirdItemCountLvl10000to100000,
            thirdItemCountDiscountMeanLvl10000to100000 = advancedFilesPropertiesCommand.thirdItemCountDiscountMeanLvl10000to100000,
            thirdItemCountDiscountStdLvl10000to100000 = advancedFilesPropertiesCommand.thirdItemCountDiscountStdLvl10000to100000,


            firstItemCountLvl100000to1000000 = advancedFilesPropertiesCommand.firstItemCountLvl100000to1000000,
            firstItemCountDiscountMeanLvl100000to1000000 = advancedFilesPropertiesCommand.firstItemCountDiscountMeanLvl100000to1000000 ,
            firstItemCountDiscountStdLvl100000to1000000 = advancedFilesPropertiesCommand.firstItemCountDiscountStdLvl100000to1000000 ,
            secondItemCountLvl100000to1000000 = advancedFilesPropertiesCommand.secondItemCountLvl100000to1000000 ,
            secondItemCountDiscountMeanLvl100000to1000000 = advancedFilesPropertiesCommand.secondItemCountDiscountMeanLvl100000to1000000 ,
            secondItemCountDiscountStdLvl100000to1000000 = advancedFilesPropertiesCommand.secondItemCountDiscountStdLvl100000to1000000 ,
            thirdItemCountLvl100000to1000000 = advancedFilesPropertiesCommand.thirdItemCountLvl100000to1000000 ,
            thirdItemCountDiscountMeanLvl100000to1000000 = advancedFilesPropertiesCommand.thirdItemCountDiscountMeanLvl100000to1000000 ,
            thirdItemCountDiscountStdLvl100000to1000000 = advancedFilesPropertiesCommand.thirdItemCountDiscountStdLvl100000to1000000 ,
        )

        val zipedData = randomZipFileServiceImpl.zipRandomFiles(data!!)

        val headers = HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=randomdata.zip");
        // defining the custom Content-Type
        headers.set(HttpHeaders.CONTENT_TYPE, "application/zip");

        return ResponseEntity(
            zipedData,
            headers,
            HttpStatus.OK
        )


    }
}