package ai.dnai.io.pseudorandomfilegenerator.fascade

import ai.dnai.io.pseudorandomfilegenerator.service.FileGenerators.PseudorandomFileGenerationService
import ai.dnai.io.pseudorandomfilegenerator.service.FileServiceImpl.RandomZipFileServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class DataGenerationFascade @Autowired constructor(
    val pseudorandomFileGenerationService: PseudorandomFileGenerationService,
    val randomZipFileServiceImpl: RandomZipFileServiceImpl){

    fun generateData(initialSeed: Int,
                     numberOfOrders: Int,
                     numberOfConsumptions: Int,
                     numberOfSuppliers: Int,
                     numberOfCurrenciesAndItems: Int,
                     initialConsumptionInstant: Instant,
                     lastConsumptionInstant: Instant,
                     ordersFromInstant: Instant,
                     ordersToInstant: Instant,
                     priceListValidFromInstant: Instant,
                     priceListValidToInstant: Instant,
                     priceListRegion: String,
                     warehouseName: String) : ByteArray{

        val generatedFiles = this.pseudorandomFileGenerationService.generatePseudorandomFileData(
            initialSeed = initialSeed,
            numberOfOrders = numberOfOrders,
            numberOfConsumptions = numberOfConsumptions,
            numberOfSuppliers = numberOfSuppliers,
            numberOfCurrenciesAndItems = numberOfCurrenciesAndItems,
            initialConsumptionInstant = initialConsumptionInstant,
            lastConsumptionInstant = lastConsumptionInstant,
            ordersFromInstant = ordersFromInstant,
            ordersToInstant = ordersToInstant,
            priceListValidFromInstant = priceListValidFromInstant,
            priceListValidToInstant = priceListValidToInstant,
            priceListRegion = priceListRegion,
            warehouseName = warehouseName)

        return randomZipFileServiceImpl.zipRandomFiles(generatedFiles)


    }
}