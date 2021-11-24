package com.housing.movie.features.person.data.repository

import com.housing.movie.features.person.domain.entity.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<Person, String> {

}