package com.housing.movie.features.person.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.person.domain.entity.Person
import com.housing.movie.features.person.domain.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SavePersonUseCase : BaseUseCase {

    @Autowired
    private lateinit var personService: PersonService

    override fun invoke(data: Any?): Any? {
        return personService.createPerson(data as Person)
    }
}