package ai.dnai.io.pseudorandomfilegenerator.service.FileGenerators

import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.CurrencyResult
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.SupplierResult
import ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity.SupplierTransportationResult
import org.springframework.stereotype.Service
import kotlin.math.absoluteValue
import kotlin.random.Random
import kotlin.random.nextInt

@Service
class SupplierTransportationFileGenerator {
    fun generateSuppliersTransportations(suppliers: List<SupplierResult>,
                                         currencies: List<CurrencyResult>,
                                         rng: Random): List<SupplierTransportationResult>{

        val supplierTransportations = ArrayList<SupplierTransportationResult>()
        for (i in suppliers){
            supplierTransportations.add(
                SupplierTransportationResult(
                    organisationId = i.organisationId,
                    currencyCode = currencies.get(rng.nextInt(0 until currencies.size)).currencyCode,
                    cost = rng.nextInt().absoluteValue
                )
            )
        }
        return supplierTransportations
    }
}