package com.afirez.spring.reactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableR2dbcRepositories
@EnableJpaRepositories
open class ReactiveApplication

fun main(args: Array<String>) {
    runApplication<ReactiveApplication>(*args)
}
