package ai.dnai.io.pseudorandomfilegenerator.api.commands

import ai.dnai.io.pseudorandomfilegenerator.service.DateTimeFormatService
import ai.dnai.io.pseudorandomfilegenerator.service.impl.DateTimeFormatServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Component
class ShifterParameterCommand{
    var daysOffsetFromNow = 0
    var datasetName = "fixcorp"
}