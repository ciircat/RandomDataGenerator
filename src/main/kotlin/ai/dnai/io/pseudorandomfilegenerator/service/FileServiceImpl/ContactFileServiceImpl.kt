package ai.dnai.io.pseudorandomfilegenerator.service.FileServiceImpl

import ai.dnai.common.api.dto.ContactDTO
import ai.dnai.io.pseudorandomfilegenerator.service.FileService.ContactFileService

open class ContactFileServiceImpl : ContactFileService {
    override fun shiftDay(contactDTO: ContactDTO, days: Long): ContactDTO {
        TODO("Not yet implemented")
    }
}