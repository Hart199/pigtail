package com.template

import co.paralleluniverse.fibers.Suspendable
import net.corda.core.flows.FlowLogic
import net.corda.core.flows.InitiatingFlow
import net.corda.core.flows.StartableByRPC
import net.corda.core.flows.StartableByService

@InitiatingFlow
@StartableByRPC
@StartableByService
class EchoFlow(val msg: String) : FlowLogic<String>() {
    @Suspendable
    override fun call() : String {
        return msg
    }
}