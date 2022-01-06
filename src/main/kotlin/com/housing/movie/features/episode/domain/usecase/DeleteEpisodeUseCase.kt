package com.housing.movie.features.episode.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.episode.domain.service.EpisodeService
import org.springframework.stereotype.Component
import java.util.*

@Component
class DeleteEpisodeUseCase(
    private val episodeService: EpisodeService
) : BaseUseCase {
    override fun invoke(data: Any?): Boolean {
        return episodeService.deleteEpisode(data as UUID)
    }
}