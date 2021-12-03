package com.housing.movie.exceptions

import com.housing.movie.base.BaseException

class ObjectAlreadyExistsException(override val message: String?) : BaseException(message) {

}