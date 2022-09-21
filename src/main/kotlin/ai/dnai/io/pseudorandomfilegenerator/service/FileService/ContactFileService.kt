package ai.dnai.io.pseudorandomfilegenerator.service.FileService

import ai.dnai.common.api.dto.ContactDTO

interface ContactFileService {
    fun shiftDay(contactDTO: ContactDTO, days: Long): ContactDTO
}