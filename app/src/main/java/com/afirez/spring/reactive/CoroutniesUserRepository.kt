//package com.afirez.spring.reactive
//
//class UserRepository(private val client: DatabaseClient) {
//
//    suspend fun count(): Long =
//            client.execute().sql("SELECT COUNT(*) FROM users")
//                    .asType<Long>().fetch().awaitOne()
//
//    fun findAll(): Flow<User> =
//            client.select().from("users").asType<User>().fetch().flow()
//
//    suspend fun findOne(id: String): User? =
//            client.execute()
//                    .sql("SELECT * FROM users WHERE login = :login")
//                    .bind("login", id).asType<User>()
//                    .fetch()
//                    .awaitOneOrNull()
//
//    suspend fun deleteAll() =
//            client.execute().sql("DELETE FROM users").await()
//
//    suspend fun save(user: User) =
//            client.insert().into<User>().table("users").using(user).await()
//
//    suspend fun init() {
//        client.execute().sql("CREATE TABLE IF NOT EXISTS users (login varchar PRIMARY KEY, firstname varchar, lastname varchar);").await()
//        deleteAll()
//        save(User("smaldini", "Stéphane", "Maldini"))
//        save(User("sdeleuze", "Sébastien", "Deleuze"))
//        save(User("bclozel", "Brian", "Clozel"))
//    }
//}
//
//@RestController
//class UserWithDetailsController(
//        private val userRepository: UserRepository,
//        private val client: WebClient) {
//
//    @GetMapping("/")
//    fun findAll(): Flow<UserWithDetails> =
//            userRepository.findAll().map(this::withDetails)
//
//    @GetMapping("/{id}")
//    suspend fun findOne(@PathVariable id: String): UserWithDetails {
//        val user: User = userRepository.findOne(id) ?:
//        throw CustomException("This user does not exist")
//        return withDetails(user)
//    }
//
//    private suspend fun withDetails(user: User): UserWithDetails {
//        val userDetail1 = client.get().uri("/userdetail1/${user.login}")
//                .accept(APPLICATION_JSON)
//                .awaitExchange().awaitBody<UserDetail1>()
//        val userDetail2 = client.get().uri("/userdetail2/${user.login}")
//                .accept(APPLICATION_JSON)
//                .awaitExchange().awaitBody<UserDetail2>()
//        return UserWithDetails(user, userDetail1, userDetail2)
//    }
//}
//
//@RestController
//class UserWithDetailsController(
//        private val userRepository: UserRepository,
//        private val client: WebClient) {
//
//    @GetMapping("/")
//    fun findAll(): Flow<UserWithDetails> =
//            userRepository.findAll().map(this::withDetails)
//
//    @GetMapping("/{id}")
//    suspend fun findOne(@PathVariable id: String): UserWithDetails {
//        val user: User = userRepository.findOne(id) ?:
//        throw CustomException("This user does not exist")
//        return withDetails(user)
//    }
//
//    private suspend fun withDetails(user: User): UserWithDetails = coroutineScope {
//        val asyncDetail1 = async {
//            client.get().uri("/userdetail1/${user.login}")
//                    .accept(APPLICATION_JSON)
//                    .awaitExchange().awaitBody<UserDetail1>()
//        }
//        val asyncDetail2 = async {
//            client.get().uri("/userdetail2/${user.login}")
//                    .accept(APPLICATION_JSON)
//                    .awaitExchange().awaitBody<UserDetail2>()
//        }
//        UserWithDetails(user, asyncDetail1.await(), asyncDetail2.await())
//    }
//}