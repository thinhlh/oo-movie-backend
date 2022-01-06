package com.housing.movie.exceptions

import com.amazonaws.services.opensearch.model.BaseException

class ConversionException(override val message: String?) : BaseException(message) {

}