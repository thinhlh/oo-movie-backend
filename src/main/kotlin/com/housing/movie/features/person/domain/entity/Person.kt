package com.housing.movie.features.person.domain.entity

import lombok.Data
import lombok.Generated
import lombok.NoArgsConstructor
import lombok.RequiredArgsConstructor
import org.hibernate.validator.constraints.CreditCardNumber
import org.jetbrains.annotations.NotNull
import java.util.*
import javax.persistence.*
import javax.validation.constraints.*

@Entity
@Table(name = "Person")
class Person(

    @Id
    @GeneratedValue
    @NotNull
    val id: UUID = UUID.randomUUID(),

    @NotBlank(message = "Name cannot be empty")
    var name: String? = null,

    @Min(value = 0, message = "Age cannot be smaller than 1")
    @Max(value = 100, message = "Age cannot be larger than 100")
    var age: Int? = null
)
