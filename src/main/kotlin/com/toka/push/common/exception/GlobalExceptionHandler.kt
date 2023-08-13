package com.toka.push.common.exception

import mu.KotlinLogging
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = KotlinLogging.logger {}

    @ExceptionHandler(com.toka.push.common.exception.ServerException::class)
    fun handleServerException(ex: com.toka.push.common.exception.ServerException): com.toka.push.common.exception.ErrorResponse {
        logger.error(ex) { ex.message }

        return com.toka.push.common.exception.ErrorResponse(code = ex.code, ex.message)
    }


    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): com.toka.push.common.exception.ErrorResponse {
        logger.error(ex) { ex.message }

        return com.toka.push.common.exception.ErrorResponse(code = 500, message = "공통 에러.")
    }
}