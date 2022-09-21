package ai.dnai.io.pseudorandomfilegenerator.service.FileService

import ai.dnai.common.api.dto.OrderItemDTO

interface OrderItemFileService {
    fun shiftDay(orderItemDTO: OrderItemDTO, days: Long): OrderItemDTO
}