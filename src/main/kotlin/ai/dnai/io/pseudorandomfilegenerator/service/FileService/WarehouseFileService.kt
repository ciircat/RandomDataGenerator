package ai.dnai.io.pseudorandomfilegenerator.service.FileService

import ai.dnai.common.api.dto.WarehouseStateDTO

interface WarehouseFileService {
    fun shiftDay(warehouseStateDTO: WarehouseStateDTO, days: Long): WarehouseStateDTO
}