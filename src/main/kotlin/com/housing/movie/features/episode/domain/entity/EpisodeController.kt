package com.housing.movie.features.episode.domain.entity

import com.housing.movie.base.BaseController
import com.housing.movie.base.BaseResponse
import com.housing.movie.features.episode.domain.usecase.GetEpisodeUseCase
import com.housing.movie.features.episode.domain.usecase.create_episode.CreateEpisodeRequest
import com.housing.movie.features.episode.domain.usecase.create_episode.CreateEpisodeUseCase
import com.housing.movie.features.movie.domain.entity.Episode
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@Tag(name = "Episode", description = "Episode APIs")
class EpisodeController(
    private val getEpisodeUseCase: GetEpisodeUseCase,
    private val createEpisodeUseCase: CreateEpisodeUseCase
) : BaseController() {

    @GetMapping("/episode")
    fun getEpisode(@RequestParam id: UUID): ResponseEntity<BaseResponse<Episode>> {
        return ResponseEntity.ok(BaseResponse.success(getEpisodeUseCase(id)))
    }

    @PostMapping("/episode")
    fun createEpisode(@RequestBody createEpisodeRequest: CreateEpisodeRequest): ResponseEntity<BaseResponse<Episode>> {
        return ResponseEntity.ok(BaseResponse.success(createEpisodeUseCase(createEpisodeRequest)))
    }


}