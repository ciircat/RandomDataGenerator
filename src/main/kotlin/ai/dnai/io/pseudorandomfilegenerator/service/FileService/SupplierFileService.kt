package ai.dnai.io.pseudorandomfilegenerator.service.FileService

import ai.dnai.common.api.dto.SupplierDTO

interface SupplierFileService {
    fun shiftDay(supplierDTO: SupplierDTO, days: Long): SupplierDTO
}