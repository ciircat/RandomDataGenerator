package ai.dnai.io.pseudorandomfilegenerator.service

import java.time.format.DateTimeFormatter

interface DateTimeFormatService {

    fun getDateTimeFormatter() : DateTimeFormatter
}