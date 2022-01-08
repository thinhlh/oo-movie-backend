package com.housing.movie.features.common.entity.extended_response

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class UserInfo(
    val id: UUID?,
    val name: String?
)