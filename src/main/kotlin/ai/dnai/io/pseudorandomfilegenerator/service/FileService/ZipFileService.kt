package ai.dnai.io.pseudorandomfilegenerator.service.FileService

interface ZipFileService {
    fun zipFiles(filesLocation: String) : ByteArray
}