package com.housing.movie.features.genre.domain.usecase.create_genre

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.genre.domain.entity.Genre
import com.housing.movie.features.genre.domain.service.GenreService
import org.springframework.stereotype.Component

@Component
class CreateGenreUseCase(
        private val genreService: GenreService
) : BaseUseCase {
    override fun invoke(data: Any?): Genre {
        return genreService.createGenre(data as CreateGenreRequest)
    }
}