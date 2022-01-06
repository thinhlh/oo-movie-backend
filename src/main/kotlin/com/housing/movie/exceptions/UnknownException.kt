package com.housing.movie.exceptions

import com.amazonaws.services.opensearch.model.BaseException

class UnknownException(override val message: String?) : BaseException(message) {
}