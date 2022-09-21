package ai.dnai.io.pseudorandomfilegenerator.service.FileServiceImpl

import ai.dnai.common.api.dto.CurrenciesDTO
import ai.dnai.io.pseudorandomfilegenerator.service.FileService.CurrenciesFileService

open class CurrenciesFileServiceImpl : CurrenciesFileService{
    override fun shiftDay(currenciesDTO: CurrenciesDTO, days: Long): CurrenciesDTO {
        currenciesDTO.dateCreated = currenciesDTO.dateCreated?.plusDays(days)
        return currenciesDTO
    }
}