package com.afirez.spring.reactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
//import org.springframework.kotlin.experimental.coroutine.EnableCoroutine
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
//@EnableCoroutine
@EnableWebFlux
open class ReactiveApplication

fun main(args: Array<String>) {
    runApplication<ReactiveApplication>(*args)
}
