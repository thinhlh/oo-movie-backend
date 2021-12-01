package com.housing.movie.features.company.data

import com.housing.movie.features.company.domain.Company
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CompanyRepository : CrudRepository<Company, UUID> {
}