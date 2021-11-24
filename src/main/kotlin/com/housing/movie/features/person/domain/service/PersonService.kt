package com.housing.movie.features.person.domain.service

import com.housing.movie.features.person.domain.entity.Person

interface PersonService {
    fun getAllPerson(): List<Person>
    fun createPerson(person: Person)
}