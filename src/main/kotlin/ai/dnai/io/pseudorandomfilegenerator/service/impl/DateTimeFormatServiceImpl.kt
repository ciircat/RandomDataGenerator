package ai.dnai.io.pseudorandomfilegenerator.service.impl

import ai.dnai.io.pseudorandomfilegenerator.service.DateTimeFormatService
import org.springframework.stereotype.Service
import java.time.format.DateTimeFormatter

@Service
open class DateTimeFormatServiceImpl : DateTimeFormatService {
    override fun getDateTimeFormatter(): DateTimeFormatter {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
    }
}