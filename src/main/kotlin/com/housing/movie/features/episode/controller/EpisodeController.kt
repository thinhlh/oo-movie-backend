package com.housing.movie.features.episode.controller

import com.housing.movie.base.BaseController
import com.housing.movie.base.BaseResponse
import com.housing.movie.features.episode.data.repository.EpisodeRepository
import com.housing.movie.features.episode.domain.usecase.DeleteEpisodeUseCase
import com.housing.movie.features.episode.domain.usecase.EnableEpisodeUseCase
import com.housing.movie.features.episode.domain.usecase.GetEpisodeUseCase
import com.housing.movie.features.episode.domain.usecase.create_episode.CreateEpisodeRequest
import com.housing.movie.features.episode.domain.usecase.create_episode.CreateEpisodeUseCase
import com.housing.movie.features.episode.domain.usecase.update_episode.UpdateEpisodeRequest
import com.housing.movie.features.episode.domain.usecase.update_episode.UpdateEpisodeUseCase
import com.housing.movie.features.episode.domain.entity.Episode
import com.housing.movie.features.episode.domain.usecase.GetEpisodesByMovieIdUseCase
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@Tag(name = "Episode", description = "Episode APIs")
class EpisodeController(
    private val getEpisodeUseCase: GetEpisodeUseCase,
    private val createEpisodeUseCase: CreateEpisodeUseCase,
    private val updateEpisodeUseCase: UpdateEpisodeUseCase,
    private val deleteEpisodeUseCase: DeleteEpisodeUseCase,
    private val enableEpisodeUseCase: EnableEpisodeUseCase,
    private val getEpisodesByMovieIdUseCase: GetEpisodesByMovieIdUseCase,
) : BaseController() {

    @GetMapping("/episode")
    fun getEpisode(@RequestParam id: UUID): ResponseEntity<BaseResponse<Episode>> {
        return successResponse(getEpisodeUseCase(id))
    }

    @PostMapping("/episode")
    fun createEpisode(@RequestBody createEpisodeRequest: CreateEpisodeRequest): ResponseEntity<BaseResponse<Episode>> {
        return successResponse(createEpisodeUseCase(createEpisodeRequest))
    }

    @PutMapping("/episode")
    fun updateEpisode(@RequestBody updateEpisodeRequest: UpdateEpisodeRequest): ResponseEntity<BaseResponse<Episode>> {
        return successResponse(updateEpisodeUseCase(updateEpisodeRequest))
    }

    @DeleteMapping("/episode")
    fun deleteEpisode(@RequestParam id: UUID): ResponseEntity<BaseResponse<Boolean>> {
        return successResponse(deleteEpisodeUseCase(id))
    }

    @PutMapping("/episode/enable")
    fun enableEpisode(@RequestParam id: UUID): ResponseEntity<BaseResponse<Episode>> {
        return successResponse(enableEpisodeUseCase(id))
    }

    @GetMapping("/episodes")
    fun getEpisodesByMovieId(@RequestParam movieId: UUID): ResponseEntity<BaseResponse<List<Episode>>> {
        return successResponse(getEpisodesByMovieIdUseCase(movieId))
    }
}