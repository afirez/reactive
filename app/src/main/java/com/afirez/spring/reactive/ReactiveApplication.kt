package com.afirez.spring.reactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ReactiveApplication

fun main(args: Array<String>) {
    runApplication<ReactiveApplication>(*args)
}
