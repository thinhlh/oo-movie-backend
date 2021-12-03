package com.housing.movie.features.genre.domain.entity

import com.fasterxml.jackson.annotation.*
import com.housing.movie.features.movie.domain.entity.Movie
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.validation.constraints.NotBlank

@Entity
class Genre(

    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    @NotBlank
    var title: String = "",

    var enabled: Boolean = true,

    @ManyToMany(mappedBy = "genres")
    @JsonIgnore
    val movies: List<Movie> = emptyList()

)

