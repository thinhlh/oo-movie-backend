package com.housing.movie.features.episode.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.episode.domain.service.EpisodeService
import com.housing.movie.features.movie.domain.entity.Episode
import org.springframework.stereotype.Component
import java.util.*

@Component
class GetEpisodeUseCase(
    private val episodeService: EpisodeService

) : BaseUseCase {
    override fun invoke(data: Any?): Episode {
        return episodeService.getEpisode(data as UUID)
    }

}