package ai.dnai.io.pseudorandomfilegenerator.service.FileServiceImpl

import ai.dnai.common.api.dto.ItemDTO
import ai.dnai.io.pseudorandomfilegenerator.service.FileService.ItemFileService

open class ItemFileServiceImpls : ItemFileService{
    override fun shiftDay(itemDTO: ItemDTO, days: Long): ItemDTO {
        itemDTO.dateCreated = itemDTO.dateCreated?.plusDays(days)
        return itemDTO
    }
}