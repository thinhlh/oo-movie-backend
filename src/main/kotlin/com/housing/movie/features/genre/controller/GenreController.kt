package com.housing.movie.features.genre.controller

import com.housing.movie.base.BaseController
import com.housing.movie.base.BaseResponse
import com.housing.movie.features.genre.domain.entity.Genre
import com.housing.movie.features.genre.domain.usecase.delete_genre.DeleteGenreUseCase
import com.housing.movie.features.genre.domain.usecase.create_genre.CreateGenreUseCase
import com.housing.movie.features.genre.domain.usecase.GetAllGenresUseCase
import com.housing.movie.features.genre.domain.usecase.RedoDeleteGenreUseCase
import com.housing.movie.features.genre.domain.usecase.create_genre.CreateGenreRequest
import com.housing.movie.features.genre.domain.usecase.delete_genre.DeleteGenreRequest
import com.housing.movie.features.genre.domain.usecase.update_genre.UpdateGenreRequest
import com.housing.movie.features.genre.domain.usecase.update_genre.UpdateGenreUseCase
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RestController
@Tag(name = "Genre", description = "Genre APIs")
class GenreController(
    private val getAllGenresUseCase: GetAllGenresUseCase,
    private val createGenreUseCase: CreateGenreUseCase,
    private val deleteGenreUseCase: DeleteGenreUseCase,
    private val redoDeleteGenreUseCase: RedoDeleteGenreUseCase,
    private val updateGenreUseCase: UpdateGenreUseCase
) : BaseController() {

    @GetMapping("/genres")
    fun getAllGenres(): ResponseEntity<BaseResponse<List<Genre>>> {
        return successResponse.body(BaseResponse.success(getAllGenresUseCase()))
    }

    @PostMapping("/genre")
    fun createGenre(@RequestBody @Valid createGenreRequest: CreateGenreRequest): ResponseEntity<BaseResponse<Genre>> {
        return successResponse.body(BaseResponse.success(createGenreUseCase(createGenreRequest)))
    }

    @DeleteMapping("/genre")
    fun deleteGenre(@Valid @RequestBody deleteGenreRequest: DeleteGenreRequest): ResponseEntity<BaseResponse<Boolean>> {
        return successResponse.body(BaseResponse.success(deleteGenreUseCase(deleteGenreRequest)))
    }

    @PutMapping("/enable-genre")
    fun redoDeleteGenre(@Valid @RequestBody deleteGenreRequest: DeleteGenreRequest): ResponseEntity<BaseResponse<Boolean>> {
        return successResponse.body(BaseResponse.success(redoDeleteGenreUseCase(deleteGenreRequest)))
    }

    @PutMapping("/genre")
    fun updateGenre(@Valid @RequestBody updateGenreRequest: UpdateGenreRequest): ResponseEntity<BaseResponse<Genre>> {
        return successResponse.body(BaseResponse.success(updateGenreUseCase(updateGenreRequest)))
    }
}