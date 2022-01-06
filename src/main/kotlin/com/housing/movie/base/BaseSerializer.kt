package com.housing.movie.base

import com.fasterxml.jackson.databind.JsonSerializer

abstract class BaseSerializer<T : Any>(
    protected open val defaultSerializer: JsonSerializer<T>? = null
) : JsonSerializer<T>()