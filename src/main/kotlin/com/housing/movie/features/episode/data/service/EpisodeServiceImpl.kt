package com.housing.movie.features.episode.data.service

import com.housing.movie.exceptions.NotFoundException
import com.housing.movie.exceptions.ObjectAlreadyExistsException
import com.housing.movie.features.episode.data.repository.EpisodeRepository
import com.housing.movie.features.episode.domain.service.EpisodeService
import com.housing.movie.features.episode.domain.usecase.create_episode.CreateEpisodeRequest
import com.housing.movie.features.episode.domain.usecase.update_episode.UpdateEpisodeRequest
import com.housing.movie.features.movie.data.repository.MovieRepository
import com.housing.movie.features.episode.domain.entity.Episode
import com.housing.movie.features.movie.domain.entity.Movie
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class EpisodeServiceImpl(
    private val episodeRepository: EpisodeRepository,
    private val movieRepository: MovieRepository,
) : EpisodeService {

    companion object {
        const val EPISODE_NOT_FOUND: String = "Episode not found"
        const val MOVIE_NOT_FOUND: String = "Movie not found"
        const val EPISODE_ALREADY_EXISTS_IN_MOVIE: String = "Episode already added to movie"
    }

    override fun getEpisode(id: UUID): Episode {
        return episodeRepository.findByIdOrNull(id) ?: throw NotFoundException(message = EPISODE_NOT_FOUND)
    }

    @Transactional
    override fun createEpisode(createEpisodeRequest: CreateEpisodeRequest): Episode {

        // If movie not exists or episode is already exists => throw error
        val movieId: UUID = createEpisodeRequest.movieId

        val movie: Movie = movieRepository.getMovieById(movieId) ?: throw NotFoundException(message = MOVIE_NOT_FOUND)

        val episodeExistsInMovie = movie.episodes.any {
            it.id == movieId
        }

        if (episodeExistsInMovie) throw ObjectAlreadyExistsException(EPISODE_ALREADY_EXISTS_IN_MOVIE)

        // Assign episode to movie's episodes
        val episode: Episode = createEpisodeRequest.toEpisode()

        episode.movie = movie

        movie.episodes.add(episode)

        return episodeRepository.save(episode)
    }

    @Transactional
    override fun updateEpisode(updateEpisodeRequest: UpdateEpisodeRequest): Episode {

        val currentEpisode =
            episodeRepository.findByIdOrNull(updateEpisodeRequest.id) ?: throw NotFoundException(EPISODE_NOT_FOUND)

        val newEpisode = updateEpisodeRequest.updateEpisode(currentEpisode)

        val movieId: UUID? = currentEpisode.movie?.id

        val movie: Movie = movieId?.let {
            movieRepository.findByIdOrNull(it)
        } ?: throw NotFoundException(MOVIE_NOT_FOUND)

        newEpisode.movie = movie

        episodeRepository.save(newEpisode)

        return newEpisode

    }

    @Transactional
    override fun deleteEpisode(id: UUID): Boolean {
        val episode = episodeRepository.findByIdOrNull(id) ?: throw NotFoundException(EPISODE_NOT_FOUND)

        episode.enabled = false

        return true
    }

    override fun enableEpisode(id: UUID): Episode {
        val episode = episodeRepository.findByIdOrNull(id) ?: throw NotFoundException(EPISODE_NOT_FOUND)

        episode.enabled = true

        return episode
    }
}