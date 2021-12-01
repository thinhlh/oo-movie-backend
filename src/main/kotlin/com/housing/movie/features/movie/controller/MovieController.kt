package com.housing.movie.features.movie.controller

import com.housing.movie.base.BaseController
import com.housing.movie.base.BaseResponse
import com.housing.movie.exceptions.NotFoundException
import com.housing.movie.features.movie.domain.entity.Movie
import com.housing.movie.features.movie.domain.usecase.GetAllMoviesUseCase
import com.housing.movie.features.movie.domain.usecase.GetMovieByTitleUseCase
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Movie", description = "Movie APIs")
class MovieController(
        private val getAllMoviesUseCase: GetAllMoviesUseCase,
        private val getMovieByTitleUseCase: GetMovieByTitleUseCase,
) : BaseController() {

    @GetMapping("/movies")
    fun getAllMovies(): ResponseEntity<BaseResponse<List<Movie>>> {
        return successResponse.body(BaseResponse.success(getAllMoviesUseCase()))
    }

    @ApiResponses(
            ApiResponse(responseCode = "200", description = "Found movie"),
            ApiResponse(responseCode = "404", description = "Movie not found"),
            ApiResponse(responseCode = "400", description = "Missing name parameter")
    )
    @GetMapping("/movie")
    fun getMovieByTitle(@RequestParam(required = true) name: String): ResponseEntity<BaseResponse<Movie>> {
        return successResponse.body(BaseResponse.success(getMovieByTitleUseCase(name)))
    }
}