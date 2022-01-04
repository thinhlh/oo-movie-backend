package com.housing.movie.features.auth.domain.entity

import com.fasterxml.jackson.annotation.JsonProperty

class Tokens(
    @JsonProperty(value = "access_token")
    val accessToken: String,
    @JsonProperty(value = "request_token")
    val refreshToken: String
)