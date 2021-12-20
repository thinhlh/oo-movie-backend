package com.housing.movie.features.genre.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.genre.domain.service.GenreService
import org.springframework.stereotype.Component
import java.util.*

@Component
class DeleteGenreUseCase(private val genreService: GenreService) : BaseUseCase {

    override fun invoke(data: Any?): Boolean {
        return genreService.deleteGenre(data as UUID)
    }
}