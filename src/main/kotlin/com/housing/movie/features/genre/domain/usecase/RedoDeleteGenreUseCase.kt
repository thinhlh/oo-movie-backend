package com.housing.movie.features.genre.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.genre.domain.service.GenreService
import com.housing.movie.features.genre.domain.usecase.delete_genre.DeleteGenreRequest
import org.springframework.stereotype.Component

@Component
class RedoDeleteGenreUseCase(private val genreService: GenreService) : BaseUseCase {
    override fun invoke(data: Any?): Boolean {
        return genreService.redoDeleteGenre((data as DeleteGenreRequest).id)
    }
}