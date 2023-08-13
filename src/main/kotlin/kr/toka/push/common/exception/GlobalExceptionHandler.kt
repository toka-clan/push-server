package kr.toka.push.common.exception

import mu.KotlinLogging
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = KotlinLogging.logger {}

    @ExceptionHandler(ServerException::class)
    fun handleServerException(ex: ServerException): ErrorResponse {
        logger.error(ex) { ex.message }

        return ErrorResponse(code = ex.code, ex.message)
    }


    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ErrorResponse {
        logger.error(ex) { ex.message }

        return ErrorResponse(code = 500, message = "공통 에러.")
    }
}