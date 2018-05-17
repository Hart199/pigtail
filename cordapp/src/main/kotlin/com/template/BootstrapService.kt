package com.template

import io.bluebank.braid.corda.BraidConfig
import io.vertx.core.http.HttpServerOptions
import net.corda.core.node.AppServiceHub
import net.corda.core.node.services.CordaService
import net.corda.core.serialization.SingletonSerializeAsToken

@CordaService
class BootstrapService(val services: AppServiceHub) : SingletonSerializeAsToken() {

    init {
        // We initiate Braid server as a service when the node starts.
        BraidConfig()
                // Include a flow on the Braid server.
                .withFlow(EchoFlow::class.java)
                // Include a service on the Braid server.
                .withService("myService", MyServiceImpl(services))
                // The port the Braid server listens on.
                .withPort(8080)
                // Using http instead of https.
                .withHttpServerOptions(HttpServerOptions().setSsl(false))
                // Start the Braid server.
                .bootstrapBraid(services)
    }
}