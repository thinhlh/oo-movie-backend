package com.housing.movie.exceptions

import com.housing.movie.base.BaseException

class NotFoundException(override val message: String?) : BaseException(message) {

}