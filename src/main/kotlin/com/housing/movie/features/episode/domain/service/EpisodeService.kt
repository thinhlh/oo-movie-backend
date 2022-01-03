package com.housing.movie.features.episode.domain.service

import com.housing.movie.features.episode.domain.usecase.create_episode.CreateEpisodeRequest
import com.housing.movie.features.episode.domain.usecase.update_episode.UpdateEpisodeRequest
import com.housing.movie.features.episode.domain.entity.Episode
import java.util.*

interface EpisodeService {
    fun getEpisode(id: UUID): Episode

    fun createEpisode(createEpisodeRequest: CreateEpisodeRequest): Episode

    fun updateEpisode(updateEpisodeRequest: UpdateEpisodeRequest): Episode

    fun deleteEpisode(id: UUID): Boolean

    fun enableEpisode(id: UUID): Episode

    fun getEpisodesByMovieId(movieId: UUID): List<Episode>
}