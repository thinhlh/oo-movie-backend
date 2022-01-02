package com.housing.movie.features.episode.domain.usecase.update_episode

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.episode.domain.service.EpisodeService
import com.housing.movie.features.episode.domain.entity.Episode
import org.springframework.stereotype.Component

@Component
class UpdateEpisodeUseCase(
    private val episodeService: EpisodeService
) : BaseUseCase {
    override fun invoke(data: Any?): Episode {
        return episodeService.updateEpisode(data as UpdateEpisodeRequest)
    }
}