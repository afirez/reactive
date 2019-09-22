package com.afirez.spring.reactive

import io.reactivex.Single
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reactive")
class ReactiveController {

    @GetMapping("/rxjava/hello")
    fun helloByRx(@RequestParam who: String?): Single<String> {
        return Single.create<String> { emitter ->
            if (who.isNullOrEmpty()) {
                emitter.tryOnError(IllegalStateException("Who are you"))
                return@create
            }
            emitter.onSuccess("hello $who")
        }
    }
}
