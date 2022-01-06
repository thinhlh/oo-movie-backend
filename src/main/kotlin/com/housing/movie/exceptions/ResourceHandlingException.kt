package com.housing.movie.exceptions

import com.amazonaws.services.opensearch.model.BaseException

class ResourceHandlingException(override val message: String?) : BaseException(message) {
}