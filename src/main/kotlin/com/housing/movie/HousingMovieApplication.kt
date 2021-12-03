package com.housing.movie

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class HousingMovieApplication


fun main(args: Array<String>) {
    runApplication<HousingMovieApplication>(*args)
}
