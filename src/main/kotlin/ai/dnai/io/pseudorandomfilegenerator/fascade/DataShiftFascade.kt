package ai.dnai.io.pseudorandomfilegenerator.fascade

import ai.dnai.io.pseudorandomfilegenerator.service.DataOffsetCalculationService
import ai.dnai.io.pseudorandomfilegenerator.service.DataShiftService
import ai.dnai.io.pseudorandomfilegenerator.service.FileService.ZipFileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File

@Service
class DataShiftFascade @Autowired constructor(
    val dataShiftService: DataShiftService,
    val dataOffsetCalculationService: DataOffsetCalculationService,
    val zipFileService: ZipFileService
) {
    fun shiftData(files: Array<File>, startDate: String, datasetName: String): ByteArray {

        val offsetByDays = dataOffsetCalculationService.calculateOffset(startDate, datasetName)

        dataShiftService.shiftData(
            files = files,
            offset = offsetByDays,
            datasetName = datasetName
        )

        return zipFileService.zipFiles(datasetName)
    }
}