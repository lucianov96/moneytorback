package com.moneytorback
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.api.gax.rpc.ApiException
import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.name.Names
import com.luriam.providers.ObjectMapperProvider
import com.moneytorback.controllers.*
import com.moneytorback.providers.CamelCaseObjectMapperProvider
import com.moneytorback.providers.ValidatorProvider
import com.moneytorback.repository.FileRepository
import com.moneytorback.repository.ImageRepository
import spark.Request
import spark.Response
import spark.ResponseTransformer
import spark.Route
import spark.Spark.delete
import spark.Spark.exception
import spark.Spark.get
import spark.Spark.options
import spark.Spark.path
import spark.Spark.port
import spark.Spark.post
import spark.Spark.put
import spark.kotlin.before
import javax.validation.Validator

class Router {
    private val injector = Guice.createInjector(Module())

    fun addRoutes() {
        val userController = injector.getInstance(UserController::class.java)
        val movementController = injector.getInstance(MovementController::class.java)
        val reportController = injector.getInstance(ReportController::class.java)
        val informController = injector.getInstance(InformController::class.java)
        val cardController = injector.getInstance(CardController::class.java)

        port(80)
        path("/user") {
            post(userController::registerUser, toJson())
            path("/login") {
                post(userController::login, toJson())
            }
        }
        path("/:id") {
            path("expenses") {
                get(movementController::getUserMovements, toJson())
                post(movementController::registerMovement, toJson())
            }
            path("entries") {
                get(movementController::getUserMovements, toJson())
                post(movementController::registerMovement, toJson())
            }
            path("reports") {
                get(reportController::getMovementsWithReport, toJson())
            }
            path("informs") {
                get(informController::getInformFromRequest, toJson())
            }
            path("cards") {
                get(cardController::getCards, toJson())
                post(cardController::registerCard, toJson())
            }
        }
        // Set the router routes :o

        loadErrorHandlerRoutes()
        cors()
    }

    private fun cors() {
        options("/*"
        ) { request, response ->

            val accessControlRequestHeaders = request
                .headers("Access-Control-Request-Headers")
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers",
                    accessControlRequestHeaders)
            }

            val accessControlRequestMethod = request
                .headers("Access-Control-Request-Method")
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods",
                    accessControlRequestMethod)
            }

            "OK"
        }

        before {
            response.header("Access-Control-Allow-Origin", "*")
            response.header("Content-Type", "Application/Json")
        }
    }

    private fun loadErrorHandlerRoutes() {
        loadBadRequestHandler()
        loadInternalServerErrorHandler()
    }

    private fun loadInternalServerErrorHandler() {
        exception(Exception::class.java) { e, request, response ->
            val mapper = injector.getInstance(ObjectMapper::class.java)
            val err = mapOf(
                "except" to e::class.java,
                "error" to e.message,
                "stack_trace" to e.stackTrace!!.contentToString()
            )
            println("[EXCEPTION] -> " + mapper.writeValueAsString(err))
            response.status(500)
            response.body(mapper.writeValueAsString(err))
        }
    }

    private fun loadBadRequestHandler() {
        exception(ApiException::class.java) { e, request, response ->
            val mapper = injector.getInstance(ObjectMapper::class.java)
            val err = mapOf(
                "except" to e::class.java,
                "error" to e.message,
                "stack_trace" to e.stackTrace!!.contentToString()
            )
            response.status(e.statusCode.code.httpStatusCode)
            response.body(mapper.writeValueAsString(err))
        }
    }

    private fun delete(function: (Request, Response) -> Any, t: ResponseTransformer) {
        return delete("", Route(function), t)
    }

    private fun post(path: String, function: (Request, Response) -> Any, t: ResponseTransformer) {
        return post(path, Route(function), t)
    }

    private fun post(function: (Request, Response) -> Any, t: ResponseTransformer) {
        return post("", Route(function), t)
    }

    private fun put(function: (Request, Response) -> Any, t: ResponseTransformer) {
        return put("", Route(function), t)
    }

    private fun get(path: String, function: (Request, Response) -> Any?, t: ResponseTransformer) {
        return get(path, Route(function), t)
    }

    private fun get(function: (Request, Response) -> Any?, t: ResponseTransformer) {
        return get("", Route(function), t)
    }

    private fun toJson(): ResponseTransformer = object : ResponseTransformer {
        val objectMapper = injector.getInstance(ObjectMapper::class.java)
        override fun render(model: Any?): String =
            objectMapper.writeValueAsString(model)
    }
}

class Module : AbstractModule() {
    override fun configure() {
        bind(ObjectMapper::class.java)
            .toProvider(ObjectMapperProvider::class.java)
        bind(ObjectMapper::class.java).annotatedWith(Names.named("camelCase"))
            .toProvider(CamelCaseObjectMapperProvider::class.java)
        bind(Validator::class.java).toProvider(ValidatorProvider::class.java)
        bind(FileRepository::class.java).toInstance(ImageRepository())
    }
}
