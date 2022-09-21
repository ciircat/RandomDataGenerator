package ai.dnai.io.pseudorandomfilegenerator.service.impl

import ai.dnai.io.pseudorandomfilegenerator.service.DataOffsetCalculationService
import org.springframework.stereotype.Service
import ai.dnai.common.api.entity.enums.DocumentType
import ai.dnai.common.api.processor.impl.*
import mu.KotlinLogging
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Collections
import kotlin.io.path.Path
import kotlin.io.path.createDirectory
import kotlin.io.path.exists

@Service
open class DataOffsetCalculationServiceImpl : DataOffsetCalculationService{

    override fun calculateOffset(startDate: String, datasetName: String): Long {
        val dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
        var startDate = startDate

        val files = File("data/$datasetName/").listFiles()
        val file = files?.firstOrNull { it.name.startsWith("consumption") }

        if (file == null) {
            //logger.warn("\"consumption.csv\" not found to calculate diff date, will select 30 days")todo: solve logger
            return 30L
        }

        if (startDate == null || startDate.isEmpty() || startDate.length < 10) {
            startDate = LocalDateTime.now().toString().substring(0, 10)
            //logger.warn("env: \"START_DAY\" was empty or invalid, will select current day: $startDate")
        }

        val reader = FileReader(file).buffered()

        for (i in 0..5) {
            reader.readLine()
        }

        val dates = ArrayList<ZonedDateTime>()
        reader.forEachLine { dates.add(ZonedDateTime.parse(it.split(",".toRegex())[1], dtf)) }

        val duration: Duration = Duration.between(Collections.min(dates), ZonedDateTime.parse(startDate, dtf))

        return duration.toDays()
    }
}