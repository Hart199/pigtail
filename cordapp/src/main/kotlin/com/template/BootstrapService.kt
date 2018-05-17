package com.template

import io.bluebank.braid.corda.BraidConfig
import net.corda.core.node.AppServiceHub
import net.corda.core.node.services.CordaService
import net.corda.core.serialization.SingletonSerializeAsToken

@CordaService
class BootstrapService(val services: AppServiceHub) : SingletonSerializeAsToken() {
    init {
        BraidConfig()
                .withFlow(EchoFlow::class.java)
                .withPort(8080)
                .bootstrapBraid(services)
    }
}