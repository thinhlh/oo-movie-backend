package com.housing.movie.features.episode.data.repository

import com.housing.movie.features.episode.domain.entity.Episode
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EpisodeRepository : CrudRepository<Episode, UUID> {

    fun getByIdAndEnabledIsTrue(id: UUID): Episode?

    fun getById(id: UUID): Episode?

    fun getByMovieId(id: UUID): List<Episode>
}