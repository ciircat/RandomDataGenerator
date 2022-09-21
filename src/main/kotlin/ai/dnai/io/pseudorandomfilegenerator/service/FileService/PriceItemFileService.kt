package ai.dnai.io.pseudorandomfilegenerator.service.FileService

import ai.dnai.common.api.dto.PriceListDTO

interface PriceItemFileService {
    fun shiftDay(priceListDTO: PriceListDTO, days: Long): PriceListDTO
}