package ai.dnai.io.pseudorandomfilegenerator.service.FileServiceImpl

import ai.dnai.common.api.dto.WarehouseStateDTO
import ai.dnai.io.pseudorandomfilegenerator.service.FileService.WarehouseFileService

open class WarehouseFileServiceImpl : WarehouseFileService{
    override fun shiftDay(warehouseStateDTO: WarehouseStateDTO, days: Long): WarehouseStateDTO {
        warehouseStateDTO.dateCreated = warehouseStateDTO.dateCreated?.plusDays(days)
        return warehouseStateDTO
    }
}