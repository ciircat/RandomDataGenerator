package ai.dnai.io.pseudorandomfilegenerator.service

import java.io.File

interface DataShiftService {
    fun shiftData(files: Array<File>, offset: Long, datasetName: String)
}