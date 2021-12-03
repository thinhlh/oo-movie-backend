package com.housing.movie.base

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.housing.movie.exceptions.NotFoundException
import com.housing.movie.exceptions.ObjectDisabledException
import com.housing.movie.exceptions.ObjectExistsException
import org.springframework.beans.TypeMismatchException
import org.springframework.boot.json.JsonParseException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import kotlin.math.log


/**
 * Current exceptions that are handled:
 * @exception HttpMessageNotReadableException
 * @exception HttpRequestMethodNotSupportedException
 * @exception MethodArgumentNotValidException
 * @exception MissingServletRequestParameterException
 * @exception MissingKotlinParameterException
 * @exception NotFoundException
 * @exception ObjectExistsException
 * @exception ObjectDisabledException
 * @exception TypeMismatchException
 * */
@RestControllerAdvice
class CustomResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleHttpRequestMethodNotSupported(
        ex: HttpRequestMethodNotSupportedException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {

        val errorResponse = BaseResponse.error(ex.message.toString())

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {

        var message: String = "Invalid body request"

        if (ex.cause is JsonMappingException) {
            message += " for fields: " + (ex.cause as JsonMappingException).path.map { reference -> reference.fieldName }
                .joinToString(separator = ",")
        }

        message += "."

        val errorResponse = BaseResponse.error(message)

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    override fun handleMethodArgumentNotValid(
        exception: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val builder = StringBuilder()

        exception.fieldErrors.forEach { error ->
            builder.append(error.field + " : " + error.defaultMessage)
        }

        return ResponseEntity(builder, HttpStatus.BAD_REQUEST)
    }


    @ExceptionHandler(MissingKotlinParameterException::class)
    private fun missingParameter(
        exception: MissingKotlinParameterException,
        request: WebRequest
    ): ResponseEntity<BaseResponse<String>> {
        val errorResponse = BaseResponse.error("Missing ${exception.parameter} field")

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    override fun handleMissingServletRequestParameter(
        ex: MissingServletRequestParameterException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errorResponse = BaseResponse(false, "Missing parameter '${ex.parameterName}' ", null)

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NotFoundException::class)
    private fun notFoundException(
        exception: NotFoundException,
        request: WebRequest
    ): ResponseEntity<BaseResponse<String>> {
        val errorResponse = BaseResponse.error(exception.message.toString())

        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ObjectExistsException::class)
    private fun objectExistsException(
        exception: ObjectExistsException,
        request: WebRequest
    ): ResponseEntity<BaseResponse<String>> {
        val errorResponse = BaseResponse.error(exception.message.toString())

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ObjectDisabledException::class)
    private fun objectDisabledException(
        exception: ObjectDisabledException,
        request: WebRequest
    ): ResponseEntity<BaseResponse<String>> {
        val errorResponse = BaseResponse.error(exception.message.toString())

        return ResponseEntity(errorResponse, HttpStatus.GONE)
    }

    override fun handleTypeMismatch(
        exception: TypeMismatchException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errorResponse = BaseResponse.error(exception.message.toString())
        logger.error(exception)

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleDefaultExceptions(exception: java.lang.Exception, request: WebRequest): ResponseEntity<Any> {
        val errorResponse = BaseResponse.error(exception.message.toString())

        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}