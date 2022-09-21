package ai.dnai.io.pseudorandomfilegenerator.service.FileService

import ai.dnai.common.api.dto.ItemDTO

interface ItemFileService {
    fun shiftDay(itemDTO: ItemDTO, days: Long): ItemDTO
}