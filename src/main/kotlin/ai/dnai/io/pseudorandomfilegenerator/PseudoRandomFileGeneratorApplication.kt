package ai.dnai.io.pseudorandomfilegenerator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PseudoRandomFileGeneratorApplication

fun main(args: Array<String>) {
    runApplication<PseudoRandomFileGeneratorApplication>(*args)
}
