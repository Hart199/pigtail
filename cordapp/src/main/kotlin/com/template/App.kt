package com.template

import co.paralleluniverse.fibers.Suspendable
import net.corda.core.flows.FlowLogic
import net.corda.core.flows.InitiatingFlow
import net.corda.core.flows.StartableByRPC
import net.corda.core.flows.StartableByService
import net.corda.core.node.AppServiceHub
import net.corda.core.utilities.getOrThrow

@InitiatingFlow
@StartableByRPC
@StartableByService
class EchoFlow(val msg: String) : FlowLogic<String>() {
    @Suspendable
    override fun call() : String {
        return msg
    }
}

interface MyService {
    fun echo(msg: String) : String
}

class MyServiceImpl(val services: AppServiceHub) : MyService {
    override fun echo(msg: String) : String {
        return services.startFlow(EchoFlow(msg)).returnValue.getOrThrow()
    }
}