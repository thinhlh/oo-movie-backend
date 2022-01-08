package com.housing.movie.exceptions

import com.amazonaws.services.opensearch.model.BaseException

class CustomAuthenticationException(override val message: String? = "Unable to authenticate user.") :
    BaseException(message) {
}