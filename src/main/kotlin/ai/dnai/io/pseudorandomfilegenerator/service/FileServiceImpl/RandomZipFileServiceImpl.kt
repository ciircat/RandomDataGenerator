package ai.dnai.io.pseudorandomfilegenerator.service.FileServiceImpl

import ai.dnai.io.pseudorandomfilegenerator.api.exceptions.UnknownDocumentTypeException
import ai.dnai.io.pseudorandomfilegenerator.entity.enums.DocumentType
import ai.dnai.io.pseudorandomfilegenerator.service.FileService.ZipFileService
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

@Service
class RandomZipFileServiceImpl {

    fun zipRandomFiles(files: Map<DocumentType, List<ByteArray> >): ByteArray {
        val baos = ByteArrayOutputStream()
        val zos = ZipOutputStream(baos)
        for (i in files){
            val fileName = when(i.key){
                DocumentType.SUPPLIER_FILE -> "suppliers.csv"
                DocumentType.CURRENCY_FILE -> "currencies.csv"
                DocumentType.ITEM_FILE -> "items.csv"
                DocumentType.SUPPLIER_TRANSPORTATION_FILE -> "suppliersTransportations.csv"
                DocumentType.PRICE_LIST_FILE -> "priceList.csv"
                DocumentType.CONSUMPTION_FILE -> "consumption.csv"
                DocumentType.WAREHOUSE_FILE -> "warehouseState.csv"
                DocumentType.ORDER_ITEM_FILE -> "orders.csv"
                else -> throw UnknownDocumentTypeException("Trying to put into zip file unknown documment type ${i.key}")
            }
            if ( !fileName.equals("priceList.csv") ){
                val file = i.value[0]
                val entry = ZipEntry(fileName);
                entry.setSize((file.size).toLong());
                zos.putNextEntry(entry);
                zos.write(file);
                zos.closeEntry();
            }else{
                for (j in 0 until i.value.size){
                    val entry = ZipEntry("priceList${j}.csv");
                    entry.setSize((i.value[j].size).toLong());
                    zos.putNextEntry(entry);
                    zos.write(i.value[j]);
                    zos.closeEntry();
                }
            }
        }
        zos.close();

        return baos.toByteArray();
    }
}