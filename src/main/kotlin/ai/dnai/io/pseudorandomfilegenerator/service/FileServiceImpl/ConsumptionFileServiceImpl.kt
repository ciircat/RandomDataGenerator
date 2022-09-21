package ai.dnai.io.pseudorandomfilegenerator.service.FileServiceImpl

import ai.dnai.common.api.dto.ConsumptionDTO
import ai.dnai.io.pseudorandomfilegenerator.service.FileService.ConsumptionFileService

open class ConsumptionFileServiceImpl : ConsumptionFileService {
    override fun shiftDay(consumptionDTO: ConsumptionDTO, days: Long): ConsumptionDTO {
        consumptionDTO.dateCreated = consumptionDTO.dateCreated?.plusDays(days)
        consumptionDTO.startDate = consumptionDTO.startDate?.plusDays(days)
        consumptionDTO.endDate = consumptionDTO.endDate?.plusDays(days)

        consumptionDTO.rows?.forEach {
            it.consumptionDate = it.consumptionDate?.plusDays(days)
        }

        return consumptionDTO
    }
}