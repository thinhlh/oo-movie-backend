package com.housing.movie.features.episode.domain.service

import com.housing.movie.features.episode.domain.usecase.create_episode.CreateEpisodeRequest
import com.housing.movie.features.movie.domain.entity.Episode
import java.util.*

interface EpisodeService {
    fun getEpisode(id: UUID): Episode

    fun createEpisode(createEpisodeRequest: CreateEpisodeRequest): Episode
}