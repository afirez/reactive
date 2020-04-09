package com.afirez.spring.reactive

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import org.intellij.lang.annotations.Flow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/coroutines")
class CoroutinesController {

    @GetMapping("/suspend")
    suspend fun suspendEndpoint(): String {
        delay(10)
        return "Hello World! Coroutines suspend!"
    }

    @GetMapping("/flow")
    fun flowEndpoint() = flow {
        delay(2000)
        emit("Coroutines flow 1")
        delay(2000)
        emit("Coroutines flow 2")
    }

    @GetMapping("/deferred")
    fun deferredEndpoint() = GlobalScope.async {
        delay(10)
        "Coroutines deferred"
    }

    @GetMapping("/sequential")
    suspend fun sequential(): List<String> {
        delay(10)
        return listOf("Coroutines sequential 1", "Coroutines sequential 2")
    }

    @GetMapping("/parallel")
    suspend fun parallel(): List<String> = coroutineScope {
        val deferredMsg1: Deferred<String> = async {
            delay(10)
            "Coroutines deferred msg 1"
        }
        val deferredMsg2: Deferred<String> = async {
            delay(10)
            "Coroutines deferred msg 2"
        }
        listOf(deferredMsg1.await(), deferredMsg2.await())
    }

    @GetMapping("/error")
    suspend fun error() {
        throw IllegalStateException("Coroutines unknown error")
    }

    @GetMapping("/cancel")
    suspend fun cancel() {
        throw CancellationException("Coroutines canceled")
    }


}