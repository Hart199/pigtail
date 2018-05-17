package com.template

import net.corda.core.crypto.SecureHash
import net.corda.core.node.ServiceHub
import net.corda.core.transactions.SignedTransaction

/**
 * Braid services are classes that define functions that Braid will expose
 * via the Braid server running on the node.
 *
 * Our service defines a single method, `getTransaction`, that takes a
 * transaction hash and returns the corresponding transaction.
 *
 * Braid services do not have to follow a specific format:
 *   * They do not have to implement a specific interface or subclass a
 *     specific class
 *   * They can take as many constructor arguments as you like
 *
 * @property serviceHub the node's `ServiceHub`.
 */
class BraidService(val serviceHub: ServiceHub) {

    /**
     * Retrieves the transaction with hash [id] from the node's transaction
     * storage.
     *
     * @param id the transaction hash to retrieve.
     * @return the transaction with hash [id].
     */
    fun getTransaction(id: SecureHash) : SignedTransaction {
        return serviceHub.validatedTransactions.getTransaction(id)
                ?: throw IllegalArgumentException("Unknown transaction.")
    }
}