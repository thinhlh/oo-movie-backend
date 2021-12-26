package com.housing.movie.features.episode.data.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.housing.movie.exceptions.NotFoundException
import com.housing.movie.exceptions.ObjectAlreadyExistsException
import com.housing.movie.features.episode.data.repository.EpisodeRepository
import com.housing.movie.features.episode.domain.service.EpisodeService
import com.housing.movie.features.episode.domain.usecase.create_episode.CreateEpisodeRequest
import com.housing.movie.features.movie.data.repository.MovieRepository
import com.housing.movie.features.movie.domain.entity.Episode
import com.housing.movie.features.movie.domain.entity.Movie
import org.springframework.stereotype.Service
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
        return episodeRepository.getByIdAndEnabledIsTrue(id) ?: throw NotFoundException(message = EPISODE_NOT_FOUND)
    }

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
        episodeRepository.save(episode)


        return episode
    }
}