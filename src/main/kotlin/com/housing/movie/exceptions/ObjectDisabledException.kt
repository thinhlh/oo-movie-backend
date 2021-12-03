package com.housing.movie.exceptions

import com.housing.movie.base.BaseException

class ObjectDisabledException(override val message: String?) : BaseException(message) {

}