package ai.dnai.io.pseudorandomfilegenerator.service.FileServiceImpl

import ai.dnai.io.pseudorandomfilegenerator.service.FileService.ZipFileService
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

@Service
class RandomZipFileServiceImpl {

    fun zipRandomFiles(files: ArrayList<ByteArray>): ByteArray {
        val baos = ByteArrayOutputStream()
        val zos = ZipOutputStream(baos)
        for (i in 0 until 8){

            val fileName = when(i){
                0 -> "suppliers.csv"
                1 -> "currencies.csv"
                2 -> "items.csv"
                3 -> "suppliersTransportations.csv"
                4-> "priceList.csv"
                5 -> "consumption.csv"
                6-> "warehouseState.csv"
                else -> "orders.csv"
            }
            val entry = ZipEntry(fileName);
            entry.setSize((files[i].size).toLong());
            zos.putNextEntry(entry);
            zos.write(files[i]);
            zos.closeEntry();
        }
        zos.close();

        return baos.toByteArray();
    }
}