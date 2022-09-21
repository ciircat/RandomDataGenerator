package ai.dnai.io.pseudorandomfilegenerator.service.FileServiceImpl
import ai.dnai.common.api.dto.SupplierDTO
import ai.dnai.io.pseudorandomfilegenerator.service.FileService.SupplierFileService

open class SupplierFileServiceImpl : SupplierFileService{
    override fun shiftDay(supplierDTO: SupplierDTO, days: Long): SupplierDTO {
        supplierDTO.dateCreated = supplierDTO.dateCreated?.plusDays(days)
        return supplierDTO
    }
}