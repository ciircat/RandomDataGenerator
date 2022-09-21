package ai.dnai.io.pseudorandomfilegenerator.service.FileServiceImpl

import ai.dnai.common.api.dto.SupplierTransportationDTO
import ai.dnai.io.pseudorandomfilegenerator.service.FileService.SupplierTransportationsFileService

open class SupplierTransportationsFileServiceImpl : SupplierTransportationsFileService {
    override fun shiftDay(supplierTransportationDTO: SupplierTransportationDTO, days: Long): SupplierTransportationDTO {
        supplierTransportationDTO.dateCreated = supplierTransportationDTO.dateCreated?.plusDays(days)
        return supplierTransportationDTO
    }
}