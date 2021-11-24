package com.housing.movie.features.person.controller

import com.housing.movie.features.person.domain.entity.Person
import com.housing.movie.features.person.domain.usecase.GetAllPersonUseCase
import com.housing.movie.features.person.domain.usecase.SavePersonUseCase
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
class PersonController {
    @Autowired
    private lateinit var getAllPersonUseCase: GetAllPersonUseCase

    @Autowired
    private lateinit var savePersonUseCase: SavePersonUseCase

    @GetMapping("/persons")
    fun getData(): List<Person> {
        return getAllPersonUseCase()
    }

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello world"
    }

    @GetMapping("/person")
    private fun saveData() {
        savePersonUseCase(Person(name = "Thinh", age = 20))
    }
}