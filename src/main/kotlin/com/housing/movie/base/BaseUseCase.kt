package com.housing.movie.base

interface BaseUseCase {
    operator fun invoke(data: Any? = null): Any?
}