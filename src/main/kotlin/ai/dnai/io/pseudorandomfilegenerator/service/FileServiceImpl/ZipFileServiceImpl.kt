package ai.dnai.io.pseudorandomfilegenerator.service.FileServiceImpl

import ai.dnai.io.pseudorandomfilegenerator.service.FileService.ZipFileService
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

@Service
open class ZipFileServiceImpl : ZipFileService{

    override fun zipFiles(filesLocation: String): ByteArray {
        val files = File("output/${filesLocation}").listFiles()
        val baos = ByteArrayOutputStream()
        val zos = ZipOutputStream(baos)
        for (i in files){
            val entry = ZipEntry(i.name)
            entry.setSize((i.totalSpace))
            zos.putNextEntry(entry);
            zos.write(i.readBytes());
            zos.closeEntry();
        }
        zos.close();

      return baos.toByteArray();
    }
}