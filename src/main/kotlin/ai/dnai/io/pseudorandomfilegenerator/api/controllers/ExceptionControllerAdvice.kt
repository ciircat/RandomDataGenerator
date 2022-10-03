package ai.dnai.io.pseudorandomfilegenerator.api.controllers

import ai.dnai.io.pseudorandomfilegenerator.api.exceptions.GeneralCustomException
import ai.dnai.io.pseudorandomfilegenerator.api.exceptions.WrongConsumptionException
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {
    private val logger = KotlinLogging.logger { }
    @ExceptionHandler
    fun handleWrongConsumptionException(exception: GeneralCustomException): ResponseEntity<String>{
        logger.error { "${exception.message} , ${exception.stackTraceToString()}" }
        return ResponseEntity(exception.message, HttpStatus.BAD_REQUEST)
    }
}