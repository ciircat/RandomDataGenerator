package ai.dnai.io.pseudorandomfilegenerator.service.FileService

import ai.dnai.common.api.dto.SupplierTransportationDTO

interface SupplierTransportationsFileService {
    fun shiftDay(supplierTransportationDTO: SupplierTransportationDTO, days: Long): SupplierTransportationDTO
}