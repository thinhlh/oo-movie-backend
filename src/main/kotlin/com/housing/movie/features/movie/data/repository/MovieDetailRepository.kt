package com.housing.movie.features.movie.data.repository

import com.housing.movie.features.movie.domain.entity.MovieDetail
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MovieDetailRepository : JpaRepository<MovieDetail, UUID> {


    fun findByMovie_Id(id: UUID): MovieDetail?

}