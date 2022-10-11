package ai.dnai.io.pseudorandomfilegenerator.service.FileGenerators.AdvancedFileGenerators

import ai.dnai.common.api.entity.Currencies
import org.springframework.stereotype.Service
import kotlin.math.absoluteValue
import kotlin.random.Random

@Service
class AdvancedCurrencyGenerationService {
    fun generateCurrencyList(rngSeed: Int, numberOfCurrencies: Int): List<Currencies> {
        val currenciesList = ArrayList<Currencies>()
        val rng = Random(rngSeed)

        for (i in 0 until numberOfCurrencies) {
            currenciesList.add(generateCurrency(rngSeed = rng.nextInt()))
        }

        return currenciesList
    }

    private fun generateCurrency(rngSeed: Int): Currencies {
        val rng = Random(rngSeed)
        val currency = Currencies()

        currency.currency_name = "Currency" + rng.nextInt().absoluteValue.toString()
        currency.currency_code = rng.nextInt().toString()
        currency.exchange_rate = rng.nextDouble(from = 1.0, until = 50.0)

        return currency
    }
}