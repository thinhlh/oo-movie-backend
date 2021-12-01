package com.housing.movie.features.genre.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.genre.domain.entity.Genre
import com.housing.movie.features.genre.domain.service.GenreService
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component

@RequiredArgsConstructor
@Component
class GetAllGenresUseCase(
        private val genreService: GenreService
) : BaseUseCase {
    override fun invoke(data: Any?): List<Genre> {
        return genreService.getAllGenres()
    }
}