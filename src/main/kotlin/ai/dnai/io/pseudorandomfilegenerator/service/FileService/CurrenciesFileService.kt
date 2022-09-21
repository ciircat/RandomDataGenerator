package ai.dnai.io.pseudorandomfilegenerator.service.FileService

import ai.dnai.common.api.dto.CurrenciesDTO

interface CurrenciesFileService {
    fun shiftDay(currenciesDTO: CurrenciesDTO, days: Long): CurrenciesDTO
}