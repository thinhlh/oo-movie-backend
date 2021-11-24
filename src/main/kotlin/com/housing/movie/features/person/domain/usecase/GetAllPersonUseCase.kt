package com.housing.movie.features.person.domain.usecase

import com.housing.movie.base.BaseUseCase
import com.housing.movie.features.person.domain.entity.Person
import com.housing.movie.features.person.domain.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Component
class GetAllPersonUseCase : BaseUseCase {

    @Autowired
    private lateinit var personService: PersonService
    override fun invoke(data: Any?): List<Person> {
        return personService.getAllPerson()
    }

}