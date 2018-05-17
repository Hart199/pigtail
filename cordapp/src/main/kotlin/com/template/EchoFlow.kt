package com.template

import co.paralleluniverse.fibers.Suspendable
import net.corda.core.crypto.SecureHash
import net.corda.core.flows.FlowLogic
import net.corda.core.flows.InitiatingFlow
import net.corda.core.flows.StartableByService
import net.corda.core.transactions.SignedTransaction

/**
 * A regular flow that takes a transaction hash and returns the corresponding
 * transaction.
 *
 * @param id the transaction hash to retrieve.
 */
@InitiatingFlow
@StartableByService
class GetTransactionFlow(val id: SecureHash) : FlowLogic<SignedTransaction>() {

    /**
     * Retrieves the transaction with hash [id] from the node's transaction
     * storage.
     *
     * @return the transaction with hash [id].
     */
    @Suspendable
    override fun call() : SignedTransaction {
        return serviceHub.validatedTransactions.getTransaction(id)
                ?: throw IllegalArgumentException("Unknown transaction.")
    }
}