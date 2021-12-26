package com.housing.movie.features.episode.domain.usecase.create_episode

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.episode.domain.service.EpisodeService
import com.housing.movie.features.movie.domain.entity.Episode
import org.springframework.stereotype.Component

@Component
class CreateEpisodeUseCase(
    private val episodeService: EpisodeService
) : BaseUseCase {
    override fun invoke(data: Any?): Episode {
        return episodeService.createEpisode(data as CreateEpisodeRequest)
    }
}