package com.housing.movie.features.movie.controller

import com.housing.movie.base.BaseController
import com.housing.movie.base.BaseResponse
import com.housing.movie.features.movie.domain.entity.Movie
import com.housing.movie.features.movie.domain.usecase.*
import com.housing.movie.features.movie.domain.usecase.assign_tags_to_movie.AssignGenresToMovieUseCase
import com.housing.movie.features.movie.domain.usecase.assign_tags_to_movie.AssignGenresToMovieRequest
import com.housing.movie.features.movie.domain.usecase.create_movie.CreateMovieRequest
import com.housing.movie.features.movie.domain.usecase.create_movie.CreateMovieUseCase
import com.housing.movie.features.movie.domain.usecase.update_movie.UpdateMovieRequest
import com.housing.movie.features.movie.domain.usecase.update_movie.UpdateMovieUseCase
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@Tag(name = "Movie", description = "Movie APIs")
class MovieController(
    private val getAllMoviesUseCase: GetAllMoviesUseCase,
    private val getMovieByTitleUseCase: GetMovieByTitleUseCase,
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val assignGenresToMovieUseCase: AssignGenresToMovieUseCase,
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase,
    private val enableMovieUseCase: EnableMovieUseCase,
    private val createMovieUseCase: CreateMovieUseCase,
    private val updateMovieUseCase: UpdateMovieUseCase,
) : BaseController() {

    @GetMapping("/movies")
    fun getAllMovies(): ResponseEntity<BaseResponse<List<Movie>>> {
        return successResponse.body(BaseResponse.success(getAllMoviesUseCase()))
    }

    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Found movie"),
        ApiResponse(responseCode = "404", description = "Movie not found"),
        ApiResponse(responseCode = "400", description = "Missing name parameter"),
        ApiResponse(responseCode = "410", description = "Movie removed")
    )
    @GetMapping("/movie")
    fun getMovieById(
        @RequestParam(required = false) id: UUID?,
        @RequestParam(required = false) title: String?
    ): ResponseEntity<BaseResponse<Movie>> {
        if (id == null && title == null) {
            throw MissingServletRequestParameterException("id", "UUID")
        }

        return successResponse.body(
            BaseResponse.success(
                if (id != null) getMovieByIdUseCase(id)
                else getMovieByTitleUseCase(title)
            )
        )
    }

    @PostMapping("/movie/genres")
    fun assignGenresToMovie(@RequestBody @Valid assignTagsToMovieRequest: AssignGenresToMovieRequest): ResponseEntity<BaseResponse<Movie>> {
        return successResponse.body(BaseResponse.success(assignGenresToMovieUseCase(assignTagsToMovieRequest)))
    }

    @GetMapping("/movies/genre")
    fun getMoviesByGenre(@RequestParam genreId: UUID): ResponseEntity<BaseResponse<List<Movie>>> {
        return successResponse.body(BaseResponse.success(getMoviesByGenreUseCase(genreId)))
    }

    @DeleteMapping("/movie")
    fun deleteMovie(@RequestParam id: UUID): ResponseEntity<BaseResponse<Boolean>> {
        return ResponseEntity.ok(BaseResponse.success(deleteMovieUseCase(id)))
    }

    @PutMapping("/movie/enable")
    fun redoDeleteMovie(@RequestParam id: UUID): ResponseEntity<BaseResponse<Movie>> {
        return ResponseEntity.ok(BaseResponse.success(enableMovieUseCase(id)))
    }

    @PostMapping("/movie")
    fun createMovie(@RequestBody @Valid createMovieRequest: CreateMovieRequest): ResponseEntity<BaseResponse<Movie>> {
        return ResponseEntity.ok(BaseResponse.success(createMovieUseCase(createMovieRequest)))
    }

    @PutMapping("/movie")
    fun updateMovie(@RequestBody @Valid updateMovieRequest: UpdateMovieRequest): ResponseEntity<BaseResponse<Movie>> {
        return ResponseEntity.ok(BaseResponse.success(updateMovieUseCase(updateMovieRequest)))
    }
}