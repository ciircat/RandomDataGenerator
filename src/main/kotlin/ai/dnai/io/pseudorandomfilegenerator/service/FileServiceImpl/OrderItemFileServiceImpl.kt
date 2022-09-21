package ai.dnai.io.pseudorandomfilegenerator.service.FileServiceImpl

import ai.dnai.common.api.dto.OrderItemDTO
import ai.dnai.io.pseudorandomfilegenerator.service.FileService.OrderItemFileService

open class OrderItemFileServiceImpl : OrderItemFileService{
    override fun shiftDay(orderItemDTO: OrderItemDTO, days: Long): OrderItemDTO {
        orderItemDTO.dateCreated = orderItemDTO.dateCreated?.plusDays(days)
        orderItemDTO.startDate = orderItemDTO.startDate?.plusDays(days)
        orderItemDTO.endDate = orderItemDTO.endDate?.plusDays(days)

        orderItemDTO.rows?.forEach {
            it.orderDate = it.orderDate?.plusDays(days)
            it.arrivalDate = it.arrivalDate?.plusDays(days)
        }

        return orderItemDTO
    }
}