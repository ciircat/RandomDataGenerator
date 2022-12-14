package ai.dnai.io.pseudorandomfilegenerator.service.FileGenerators

import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.CurrencyResult
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.ItemResult
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.SupplierResult
import org.springframework.stereotype.Service
import kotlin.math.absoluteValue
import kotlin.random.Random
import kotlin.random.nextInt

@Service
class IndependentFilesGenerator {
    val STANDARD_DEVIATION_PERCENTAGE_OF_MEAN = 0.1

    fun generateSupplierResultList(rng: Random, numberOfSuppliersToGenerate: Int): List<SupplierResult> {
        val suppliers = ArrayList<SupplierResult>()
        for (i in 0 until numberOfSuppliersToGenerate) {
            suppliers.add(this.generateOrganisation(rng))
        }
        return suppliers
    }

    fun generateCurrencies(rng: Random, numberOfCurrenciesToGenerate: Int): List<CurrencyResult> {
        val currencies = ArrayList<CurrencyResult>()
        for (i in 0 until numberOfCurrenciesToGenerate) {
            currencies.add(this.generateCurrency(rng))
        }

        return currencies
    }

    fun generateItems(rng: Random, numberOfItemsToGenerate: Int): List<ItemResult> {
        val items = ArrayList<ItemResult>()
        for (i in 0 until numberOfItemsToGenerate) {
            items.add(this.generateItem(rng))
        }

        return items
    }

    private fun generateOrganisation(rng: Random): SupplierResult {
        return SupplierResult(
            organisationId = "organisation" + rng.nextInt(),
            companyId = "company" + rng.nextInt(),
            companyName = "companyName" + rng.nextInt(),
            companyDescription = "the description of this company is: " + rng.nextInt(),
            vatId = "vat" + rng.nextInt(),
            webside = "www." + rng.nextInt() + ".ru"
        )
    }

    private fun generateCurrency(rng: Random): CurrencyResult {
        return CurrencyResult(
            currencyCode = rng.nextInt().toString(),
            currencyName = "name" + rng.nextInt(),
            exchangeRate =  rng.nextInt(1 .. 50).toString()
        )
    }

    private fun generateItem(rng: Random): ItemResult {
        val mean = rng.nextLong().absoluteValue.toDouble()
        return ItemResult(
            itemNumber = rng.nextInt().absoluteValue.toString(),
            description = "description" + rng.nextInt(),
            storageCost = rng.nextInt().absoluteValue,
            category = "category" + rng.nextInt(),
            abcCategory = "abcCategory" + rng.nextInt(),
            unit = "unit" + rng.nextInt(),
            basePriceMean = rng.nextLong().absoluteValue.toDouble(),
            basePriceStandardDeviation = mean * STANDARD_DEVIATION_PERCENTAGE_OF_MEAN
        )
    }
}