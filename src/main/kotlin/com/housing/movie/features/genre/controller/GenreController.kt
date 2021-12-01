package com.housing.movie.features.genre.controller

import com.housing.movie.base.BaseController
import com.housing.movie.base.BaseResponse
import com.housing.movie.features.genre.domain.entity.Genre
import com.housing.movie.features.genre.domain.usecase.create_genre.CreateGenreUseCase
import com.housing.movie.features.genre.domain.usecase.GetAllGenresUseCase
import com.housing.movie.features.genre.domain.usecase.create_genre.CreateGenreRequest
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@Tag(name = "Genre", description = "Genre APIs")
class GenreController(
        private val getAllGenresUseCase: GetAllGenresUseCase,
        private val createGenreUseCase: CreateGenreUseCase,
) : BaseController() {

    @GetMapping("/genres")
    fun getAllGenres(): ResponseEntity<BaseResponse<List<Genre>>> {
        return successResponse.body(BaseResponse.success(getAllGenresUseCase()))
    }

    @PostMapping("/genre")
    fun createGenre(@RequestBody @Valid createGenreRequest: CreateGenreRequest): ResponseEntity<BaseResponse<Genre>> {
        return successResponse.body(BaseResponse.success(createGenreUseCase(createGenreRequest)))
    }
}