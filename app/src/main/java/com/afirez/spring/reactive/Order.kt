package com.afirez.spring.reactive

import kotlinx.coroutines.flow.Flow
import org.springframework.data.annotation.Id
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import javax.persistence.Entity


/**
 * @see orders.sql
 */
//@Entity
@Table("orders")
data class Orders(
//        @javax.persistence.Id
        @Id var id: Long = 0,
        var fn: String = ""
)


interface OrdersReactiveRepository : ReactiveCrudRepository<Orders, Long> {
    @Query("SELECT * FROM orders LIMIT :limit OFFSET :offset")
    fun findById(offset: Int, limit: Int): Flux<Orders>
}

@RestController
@RequestMapping("/reactive")
class OrdersReactiveController {
    @Autowired
    lateinit var ordersRepository: OrdersReactiveRepository

    @GetMapping("/order_all")
    fun findAll(): Flux<Orders> {
        return ordersRepository.findAll()
    }

    @GetMapping("/init")
    fun init(): Int {
        for (i in 1..30) {
            val orders = Orders()
            orders.id = i.toLong()
            orders.fn = "alpha$i"
            ordersRepository.save(orders)
        }
        return 0
    }

    @GetMapping("/page")
    fun page(): Flux<Orders> {
        return ordersRepository.findById(0, 10)
    }
}


interface OrdersRepository : CoroutineCrudRepository<Orders, Long> {
    @Query("SELECT * FROM orders LIMIT :limit OFFSET :offset")
    fun findById(offset: Int, limit: Int): Flow<Orders>
}

@RestController
@RequestMapping("/coroutine")
class OrdersController {
    @Autowired
    lateinit var ordersRepository: OrdersRepository

    @GetMapping("/order_all")
    fun findAll(): Flow<Orders> {
        return ordersRepository.findAll()
    }

    @GetMapping("/init")
    suspend fun init(): Int  {
        for (i in 1..30) {
            val orders = Orders()
            orders.id =  i.toLong()
            orders.fn = "alpha$i"
            ordersRepository.save(orders)
        }
        return 0
    }

    @GetMapping("/page")
    fun page(): Flow<Orders> {
        return ordersRepository.findById(0, 10)
    }
}