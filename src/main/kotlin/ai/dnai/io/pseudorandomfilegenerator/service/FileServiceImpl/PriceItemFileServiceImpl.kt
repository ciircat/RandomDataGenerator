package ai.dnai.io.pseudorandomfilegenerator.service.FileServiceImpl
import ai.dnai.common.api.dto.PriceListDTO
import ai.dnai.io.pseudorandomfilegenerator.service.FileService.PriceItemFileService

open class PriceItemFileServiceImpl : PriceItemFileService {
    override fun shiftDay(priceListDTO: PriceListDTO, days: Long): PriceListDTO {
        priceListDTO.startDate = priceListDTO.startDate?.plusDays(days)
        priceListDTO.endDate = priceListDTO.endDate?.plusDays(days)
        return priceListDTO
    }
}