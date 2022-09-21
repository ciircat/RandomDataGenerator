package ai.dnai.io.pseudorandomfilegenerator.utils

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class DateUtils {
    val DEFAULT_ZONE_OFFSET = ZoneOffset.UTC
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssxxxxx").withZone(ZoneId.from(DEFAULT_ZONE_OFFSET));

    fun localDateToString(localDate: LocalDate): String{
        return ZonedDateTime.of(localDate, LocalTime.MIN, DEFAULT_ZONE_OFFSET).truncatedTo(ChronoUnit.SECONDS).format(formatter);
    }
}