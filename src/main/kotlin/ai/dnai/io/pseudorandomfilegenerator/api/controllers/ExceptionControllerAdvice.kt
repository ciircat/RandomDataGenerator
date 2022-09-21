package ai.dnai.io.pseudorandomfilegenerator.api.controllers

import ai.dnai.io.pseudorandomfilegenerator.api.exceptions.GeneralCustomException
import ai.dnai.io.pseudorandomfilegenerator.api.exceptions.WrongConsumptionException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {
    @ExceptionHandler
    fun handleWrongConsumptionException(exception: GeneralCustomException): ResponseEntity<String>{
        return ResponseEntity(exception.message, HttpStatus.BAD_REQUEST)
    }
}