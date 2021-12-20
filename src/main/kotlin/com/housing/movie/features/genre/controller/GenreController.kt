package com.housing.movie.features.genre.controller

import com.housing.movie.base.BaseController
import com.housing.movie.base.BaseResponse
import com.housing.movie.features.genre.domain.entity.Genre
import com.housing.movie.features.genre.domain.usecase.DeleteGenreUseCase
import com.housing.movie.features.genre.domain.usecase.create_genre.CreateGenreUseCase
import com.housing.movie.features.genre.domain.usecase.GetAllGenresUseCase
import com.housing.movie.features.genre.domain.usecase.EnableGenreUseCase
import com.housing.movie.features.genre.domain.usecase.create_genre.CreateGenreRequest
import com.housing.movie.features.genre.domain.usecase.update_genre.UpdateGenreRequest
import com.housing.movie.features.genre.domain.usecase.update_genre.UpdateGenreUseCase
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@Tag(name = "Genre", description = "Genre APIs")
class GenreController(
    private val getAllGenresUseCase: GetAllGenresUseCase,
    private val createGenreUseCase: CreateGenreUseCase,
    private val deleteGenreUseCase: DeleteGenreUseCase,
    private val enableGenreUseCase: EnableGenreUseCase,
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
    fun deleteGenre(@RequestParam id: UUID): ResponseEntity<BaseResponse<Boolean>> {
        return successResponse.body(BaseResponse.success(deleteGenreUseCase(id)))
    }

    @PutMapping("/genre/enable")
    fun redoDeleteGenre(@RequestParam id: UUID): ResponseEntity<BaseResponse<Boolean>> {
        return successResponse.body(BaseResponse.success(enableGenreUseCase(id)))
    }

    @PutMapping("/genre")
    fun updateGenre(@Valid @RequestBody updateGenreRequest: UpdateGenreRequest): ResponseEntity<BaseResponse<Genre>> {
        return successResponse.body(BaseResponse.success(updateGenreUseCase(updateGenreRequest)))
    }
}