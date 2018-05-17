package com.template

import co.paralleluniverse.fibers.Suspendable
import net.corda.core.flows.FlowLogic
import net.corda.core.flows.InitiatingFlow
import net.corda.core.flows.StartableByRPC

@InitiatingFlow
@StartableByRPC
class Initiator : FlowLogic<Unit>() {
    @Suspendable
    override fun call() {

    }
}
