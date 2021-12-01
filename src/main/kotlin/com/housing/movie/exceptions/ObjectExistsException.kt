package com.housing.movie.exceptions

import com.housing.movie.base.BaseException

class ObjectExistsException(override val message: String?) : BaseException(message) {

}