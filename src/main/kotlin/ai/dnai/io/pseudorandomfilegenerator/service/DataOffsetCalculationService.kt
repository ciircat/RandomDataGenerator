package ai.dnai.io.pseudorandomfilegenerator.service

interface DataOffsetCalculationService {
    fun calculateOffset(startDate: String, datasetName: String): Long
}