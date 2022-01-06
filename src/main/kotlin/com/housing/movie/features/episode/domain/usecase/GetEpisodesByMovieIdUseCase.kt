package com.housing.movie.features.episode.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.episode.domain.entity.Episode
import com.housing.movie.features.episode.domain.service.EpisodeService
import org.springframework.stereotype.Component
import java.util.*

@Component
class GetEpisodesByMovieIdUseCase(
    private val episodeService: EpisodeService
) : BaseUseCase {
    override fun invoke(data: Any?): List<Episode> {
        return episodeService.getEpisodesByMovieId(data as UUID)
    }
}