package ai.dnai.io.pseudorandomfilegenerator.api.controllers

import ai.dnai.io.pseudorandomfilegenerator.api.commands.ShifterParameterCommand
import ai.dnai.io.pseudorandomfilegenerator.api.exceptions.DemonstrationDataNotFoundException
import ai.dnai.io.pseudorandomfilegenerator.api.exceptions.StartDateParsingException
import ai.dnai.io.pseudorandomfilegenerator.fascade.DataShiftFascade
import ai.dnai.io.pseudorandomfilegenerator.service.DataOffsetCalculationService
import ai.dnai.io.pseudorandomfilegenerator.service.DataShiftService
import ai.dnai.io.pseudorandomfilegenerator.service.DateTimeFormatService
import ai.dnai.io.pseudorandomfilegenerator.service.FileService.ZipFileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.io.ByteArrayOutputStream
import java.io.File
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

@RestController
class ShifterController @Autowired constructor(
    val dataShiftFascade: DataShiftFascade,
    val dateTimeFormatService: DateTimeFormatService,
){
    @PostMapping("/offsetAndGetData")
    fun offsetAndGetData(@ModelAttribute("shifterParemeterCommand") shifterParameterCommand: ShifterParameterCommand): ResponseEntity<ByteArray> {
        var startDate : String
        val datasetName = shifterParameterCommand.datasetName
        try {
            startDate = ZonedDateTime.parse(shifterParameterCommand.startDate).format(dateTimeFormatService.getDateTimeFormatter())
        }catch (exception: RuntimeException){
            throw StartDateParsingException("Unable to parse startDate parameter. Given: ${shifterParameterCommand.startDate} , expected startDate with format like ${ZonedDateTime.now().toString()}" )
        }

        var files = File("data/${datasetName}").listFiles()
        if (files == null){
            throw DemonstrationDataNotFoundException("There are no demonstration data")
        }
        val data = dataShiftFascade.shiftData(files,startDate,datasetName)

        val headers = HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=dataForShowing.zip");
        // defining the custom Content-Type
        headers.set(HttpHeaders.CONTENT_TYPE, "application/zip");

        return ResponseEntity(
            data,
            headers,
            HttpStatus.OK)

    }
}