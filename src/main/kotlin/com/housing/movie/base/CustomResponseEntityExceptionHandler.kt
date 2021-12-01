package com.housing.movie.base

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.housing.movie.exceptions.NotFoundException
import com.housing.movie.exceptions.ObjectExistsException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*


/**
 * Current exceptions that are handled:
 * @exception NotFoundException
 * @exception MissingServletRequestParameterException
 * */
@RestControllerAdvice
class CustomResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [NotFoundException::class])
    private fun notFoundException(exception: NotFoundException, request: WebRequest): ResponseEntity<BaseResponse<String>> {
        val errorResponse = BaseResponse.error(exception.message.toString())

        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val errorResponse = BaseResponse.error("Missing ${ex.parameter} field")

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [MissingKotlinParameterException::class])
    private fun missingParameter(exception: MissingKotlinParameterException, request: WebRequest): ResponseEntity<BaseResponse<String>> {
        val errorResponse = BaseResponse.error("Missing ${exception.parameter} field")

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    override fun handleHttpMessageNotReadable(exception: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val errorResponse = BaseResponse.error("Invalid body request")

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [ObjectExistsException::class])
    private fun objectExistsException(exception: ObjectExistsException, request: WebRequest): ResponseEntity<BaseResponse<String>> {
        val errorResponse = BaseResponse.error(exception.message.toString())

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    override fun handleMissingServletRequestParameter(ex: MissingServletRequestParameterException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val errorResponse = BaseResponse(false, "Missing parameter '${ex.parameterName}' ", null)

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}