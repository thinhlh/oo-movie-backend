package com.housing.movie.base


data class BaseResponse<T>(
    val success: Boolean,
    val message: String?,
    val data: T?

) {
    companion object {
        fun <T> success(data: T?): BaseResponse<T> {
            return BaseResponse(success = true, message = null, data = data)
        }

        fun error(message: String): BaseResponse<String> {
            return BaseResponse(success = false, message = message, data = null)
        }
    }
}

