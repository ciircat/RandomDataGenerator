package ai.dnai.io.pseudorandomfilegenerator.service.FileService

import ai.dnai.common.api.dto.ConsumptionDTO

interface ConsumptionFileService {
    fun shiftDay(consumptionDTO: ConsumptionDTO, days: Long): ConsumptionDTO
}