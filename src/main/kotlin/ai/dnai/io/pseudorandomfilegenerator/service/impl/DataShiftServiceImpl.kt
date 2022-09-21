package ai.dnai.io.pseudorandomfilegenerator.service.impl

import ai.dnai.common.api.entity.enums.DocumentType
import ai.dnai.common.api.processor.impl.*
import ai.dnai.io.pseudorandomfilegenerator.api.exceptions.UnknownDocumentTypeException
import ai.dnai.io.pseudorandomfilegenerator.service.DataShiftService
import ai.dnai.io.pseudorandomfilegenerator.service.FileServiceImpl.*
import lombok.extern.slf4j.Slf4j
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import kotlin.io.path.Path
import kotlin.io.path.createDirectory
import kotlin.io.path.exists

private val logger = KotlinLogging.logger { }

@Service
open class DataShiftServiceImpl : DataShiftService {
    val consumptionFileService = ConsumptionFileServiceImpl()
    private val currenciesFileService = CurrenciesFileServiceImpl()
    private val itemFileService = ItemFileServiceImpls()
    private val orderItemFileService = OrderItemFileServiceImpl()
    private val priceItemFileService = PriceItemFileServiceImpl()
    private val supplierFileService = SupplierFileServiceImpl()
    private val supplierTransportationsService = SupplierTransportationsFileServiceImpl()
    private val warehouseFileService = WarehouseFileServiceImpl()
    private val fileProcessor = FileProcessorImpl()

    override fun shiftData(files: Array<File>, offset: Long, datasetName: String) {

        if (!File("././output/${datasetName}/").exists()) {
            if (!Path("././output/").exists()) {
                Path("././output/").createDirectory()
            }
            Path("././output/${datasetName}/").createDirectory()
            logger.info { "Created output dir: output/$datasetName" }
        }

        files.forEach { file ->

            when (fileProcessor.getFileType(FileReader(file).buffered())) {
            DocumentType.CONSUMPTION -> {
                consumptionFileShiftDate(file, offset, datasetName)
            }

            DocumentType.CURRENCIES -> {
                currenciesFileShiftDate(file, offset, datasetName)
            }

            DocumentType.ITEM -> {
                itemFileShiftDate(file, offset, datasetName)
            }

            DocumentType.ORDER_ITEM -> {
                orderItemFileShiftDate(file, offset, datasetName)
            }

            DocumentType.PRICE_LIST -> {
                priceListFileShiftDate(file, offset, datasetName)
            }

            DocumentType.SUPPLIER -> {
                supplierFileShiftDate(file, offset, datasetName)
            }

            DocumentType.SUPPLIER_TRANSPORTATIONS -> {
                supplierTransportationsShiftDate(file, offset, datasetName)
            }

            DocumentType.WAREHOUSE -> {
                warehouseStateFileShiftDate(file, offset, datasetName)
            }

            else -> {
                throw UnknownDocumentTypeException("Unknown DocumentType")
            }
        } }

    }

    fun consumptionFileShiftDate(file: File, offset: Long, datasetName: String) {
        logger.info { "Start date shifting in: ${file.name}" }
        val consumptionFileProcessor = ConsumptionFileProcessorImpl()
        val consumptionDTO = consumptionFileProcessor.loadFile(BufferedReader(FileReader(file.absolutePath)))
        val arrayByte = consumptionFileProcessor.toArrayByte(
            consumptionFileService.shiftDay(
                consumptionDTO, offset
            )
        )
        File("output/${datasetName}/${file.name}").writeBytes(arrayByte)
        logger.info { "Dates in the: consumption file have been shifted and saved in output/${datasetName}/" }
    }

    fun currenciesFileShiftDate(file: File, offset: Long, datasetName: String) {
        logger.info { "Start date shifting in: ${file.name}" }
        val currenciesFileServiceImpl = CurrenciesFileProcessorImpl()
        val currenciesDTO = currenciesFileServiceImpl.loadFile(BufferedReader(FileReader(file.absolutePath)))
        val arrayByte = currenciesFileServiceImpl.toArrayByte(
            currenciesFileService.shiftDay(
                currenciesDTO, offset
            )
        )
        File("output/${datasetName}/${file.name}").writeBytes(arrayByte)
        logger.info { "Dates in the: currencies file have been shifted and saved in output/${datasetName}/" }
    }

    fun itemFileShiftDate(file: File, offset: Long, datasetName: String) {
        logger.info { "Start date shifting in: ${file.name}" }
        val itemFileProcessor = ItemFileProcessorImpl()
        val itemDTO = itemFileProcessor.loadFile(BufferedReader(FileReader(file.absolutePath)))
        val arrayByte = itemFileProcessor.toArrayByte(itemFileService.shiftDay(itemDTO, offset))
        File("output/${datasetName}/${file.name}").writeBytes(arrayByte)
        logger.info { "Dates in the: items file have been shifted and saved in output/${datasetName}/" }
    }

    fun orderItemFileShiftDate(file: File, offset: Long, datasetName: String) {
        logger.info { "Start date shifting in: ${file.name}" }
        val orderItemFileProcessor = OrderItemsFileProcessorImpl()
        val orderItemDTO = orderItemFileProcessor.loadFile(BufferedReader(FileReader(file.absolutePath)))
        val arrayByte = orderItemFileProcessor.toArrayByte(orderItemFileService.shiftDay(orderItemDTO, offset))
        File("output/${datasetName}/${file.name}").writeBytes(arrayByte)
        logger.info { "Dates in the: orderItems file have been shifted and saved in output/${datasetName}/" }
    }

    fun priceListFileShiftDate(file: File, offset: Long, datasetName: String) {
        logger.info { "Start date shifting in: ${file.name}" }
        val priceListFileProcessor = PriceListFileProcessorImpl()
        val itemDTO = priceListFileProcessor.loadFile(BufferedReader(FileReader(file.absolutePath)))
        val arrayByte = priceListFileProcessor.toArrayByte(priceItemFileService.shiftDay(itemDTO, offset))
        File("output/${datasetName}/${file.name}").writeBytes(arrayByte)
        logger.info { "Dates in the: priceList file have been shifted and saved in output/${datasetName}/" }
    }

    fun supplierFileShiftDate(file: File, offset: Long, datasetName: String) {
        logger.info { "Start date shifting in: ${file.name}" }
        val supplierFileProcessor = SupplierFileProcessorImpl()
        val supplierDTO = supplierFileProcessor.loadFile(BufferedReader(FileReader(file.absolutePath)))
        val arrayByte = supplierFileProcessor.toArrayByte(supplierFileService.shiftDay(supplierDTO, offset))
        File("output/${datasetName}/${file.name}").writeBytes(arrayByte)
        logger.info { "Dates in the: suppliers file have been shifted and saved in output/${datasetName}/" }
    }

    fun supplierTransportationsShiftDate(file: File, offset: Long, datasetName: String) {
        logger.info { "Start date shifting in: ${file.name}" }
        val transportationsProcessorImpl = SupplierTransportationsProcessorImpl()
        val supplierTransportationDTO =
            transportationsProcessorImpl.loadFile(BufferedReader(FileReader(file.absolutePath)))
        val arrayByte = transportationsProcessorImpl.toArrayByte(
            supplierTransportationsService.shiftDay(
                supplierTransportationDTO,
                offset
            )
        )
        File("output/${datasetName}/${file.name}").writeBytes(arrayByte)
        logger.info { "Dates in the: suppliersTransportations file have been shifted and saved in output/${datasetName}/" }
    }

    fun warehouseStateFileShiftDate(file: File, offset: Long, datasetName: String) {
        logger.info { "Start date shifting in: ${file.name}" }
        val warehouseStateFileProcessor = WarehouseStateFileProcessorImpl()
        val warehouseStateDTO = warehouseStateFileProcessor.loadFile(BufferedReader(FileReader(file.absolutePath)))
        val arrayByte =
            warehouseStateFileProcessor.toArrayByte(warehouseFileService.shiftDay(warehouseStateDTO, offset))
        File("output/${datasetName}/${file.name}").writeBytes(arrayByte)
        logger.info { "Dates in the: warehouseState file have been shifted and saved in output/${datasetName}/" }
    }
}