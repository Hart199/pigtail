package com.template

import io.bluebank.braid.corda.BraidConfig
import io.bluebank.braid.corda.restafarian.Restafarian
import io.bluebank.braid.corda.router.Routers
import io.swagger.models.Scheme
import io.vertx.core.http.HttpServerOptions
import net.corda.core.node.AppServiceHub
import net.corda.core.node.services.CordaService
import net.corda.core.serialization.SingletonSerializeAsToken

@CordaService
class BootstrapService(val services: AppServiceHub) : SingletonSerializeAsToken() {
    init {
        val myService = MyServiceImpl(services)
        val port = 8080

        // Creating a Braid server.
        val server = BraidConfig()
                .withFlow(EchoFlow::class.java)
                .withService(myService)
                .withPort(port)
                .withHttpServerOptions(HttpServerOptions().setSsl(false))
                .bootstrapBraid(services)

        // Adding a Swagger REST API to the Braid server.
        val router = Routers.create(server.vertx, port)
        Restafarian.mount(scheme = Scheme.HTTP, vertx = server.vertx, router = router) {
            this.put("/echo/:msg", myService::echo)
        }
    }
}