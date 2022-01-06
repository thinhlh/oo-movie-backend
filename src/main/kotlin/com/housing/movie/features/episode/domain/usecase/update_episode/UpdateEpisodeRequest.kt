package com.housing.movie.features.episode.domain.usecase.update_episode

import com.housing.movie.features.episode.domain.entity.Episode
import java.util.*
import javax.validation.constraints.NotBlank

data class UpdateEpisodeRequest(
    @NotBlank
    val id: UUID,

    val name: String? = null,

    val content: String? = null,

    val ordinal: Int? = null
) {
    fun updateEpisode(oldEpisode: Episode): Episode {
        oldEpisode.name = name ?: oldEpisode.name
        oldEpisode.content = content ?: oldEpisode.content
        oldEpisode.ordinal = ordinal ?: oldEpisode.ordinal

        return oldEpisode
    }
}