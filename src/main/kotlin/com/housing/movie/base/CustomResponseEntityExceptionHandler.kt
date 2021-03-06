package com.housing.movie.base

import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.housing.movie.exceptions.*
import org.hibernate.exception.ConstraintViolationException
import org.springframework.beans.TypeMismatchException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.core.AuthenticationException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


/**
 * Current exceptions that are handled:
 * @exception AuthenticationException
 * @exception ConversionException
 * @exception ConstraintViolationException
 * @exception HttpMessageNotReadableException
 * @exception HttpRequestMethodNotSupportedException
 * @exception MethodArgumentNotValidException
 * @exception MissingServletRequestParameterException
 * @exception MissingKotlinParameterException
 * @exception MissingPropertyException
 * @exception NotFoundException
 * @exception ObjectAlreadyExistsException
 * @exception ObjectDisabledException
 * @exception ResourceHandlingException
 * @exception SuccessIsFalseException
 * @exception TypeMismatchException
 * @exception RunOutOfUsageException
 * @exception UnknownException
 * */
@RestControllerAdvice
class CustomResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [AuthenticationException::class, InternalAuthenticationServiceException::class, BadCredentialsException::class])
    fun handleUnAuthenticatedException(
        exception: MissingKotlinParameterException,
        request: WebRequest
    ): ResponseEntity<BaseResponse<String>> {
        val errorResponse = BaseResponse.error("Unauthenticated request")

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse)
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun constraintViolationException(
        exception: ConstraintViolationException,
        request: WebRequest
    ): ResponseEntity<BaseResponse<String>> {
        val errorResponse = BaseResponse.error("${exception.constraintName} must be unique")

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(value = [CustomAuthenticationException::class])
    fun handleCustomAuthenticationException(
        exception: CustomAuthenticationException,
        request: WebRequest
    ): ResponseEntity<BaseResponse<String>> {
        val errorResponse = BaseResponse.error(exception.message.toString())

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

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

    @ExceptionHandler(MissingPropertyException::class)
    private fun missingProperty(
        exception: MissingPropertyException,
        request: WebRequest
    ): ResponseEntity<BaseResponse<String>> {
        val errorResponse = BaseResponse.error(exception.message ?: "Missing property")

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

    @ExceptionHandler(ObjectAlreadyExistsException::class)
    private fun objectExistsException(
        exceptionAlready: ObjectAlreadyExistsException,
        request: WebRequest
    ): ResponseEntity<BaseResponse<String>> {
        val errorResponse = BaseResponse.error(exceptionAlready.message.toString())

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

    @ExceptionHandler(SuccessIsFalseException::class)
    private fun handleSuccessIsFalseException(
        exception: SuccessIsFalseException,
        request: WebRequest
    ): ResponseEntity<BaseResponse<String>> {
        val errorResponse = BaseResponse.error(exception.message)

        return ResponseEntity.ok(errorResponse)
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

    @ExceptionHandler(value = [ConversionException::class, ResourceHandlingException::class, Exception::class])
    fun handleDefaultExceptions(exception: java.lang.Exception, request: WebRequest): ResponseEntity<Any> {
        val errorResponse = BaseResponse.error(exception.message.toString())

        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(value = [RunOutOfUsageException::class])
    fun handleRunOfUsageException(exception: RunOutOfUsageException, request: WebRequest): ResponseEntity<Any> {
        val errorResponse = BaseResponse.error(exception.message.toString())

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [UnknownException::class])
    fun handleUnknownException(exception: UnknownException, request: WebRequest): ResponseEntity<Any> {
        val errorResponse = BaseResponse.error(exception.message.toString())

        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}