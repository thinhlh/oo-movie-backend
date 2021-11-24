package com.housing.movie.features.person.data.service

import com.housing.movie.features.person.data.repository.PersonRepository
import com.housing.movie.features.person.domain.entity.Person
import com.housing.movie.features.person.domain.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PersonServiceImpl : PersonService {

    @Autowired
    private lateinit var personRepository: PersonRepository

    override fun getAllPerson(): List<Person> {
        return personRepository.findAll()
    }

    override fun createPerson(person: Person) {
        personRepository.save(person)
    }

}