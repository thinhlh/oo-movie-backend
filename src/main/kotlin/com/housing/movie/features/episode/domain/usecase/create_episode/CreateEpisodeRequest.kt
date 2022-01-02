package com.housing.movie.features.episode.domain.usecase.create_episode

import com.housing.movie.features.episode.domain.entity.Episode
import java.util.*

data class CreateEpisodeRequest(
    val movieId: UUID,
    val name: String = "",
    val content: String = "",
    val ordinal: Int = 0,
) {
    fun toEpisode(): Episode {
        return Episode(
            name = this.name,
            content = this.content,
            ordinal = this.ordinal
        )
    }
}