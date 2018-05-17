package com.template

import net.corda.core.identity.CordaX500Name
import net.corda.core.utilities.getOrThrow
import net.corda.testing.driver.DriverParameters
import net.corda.testing.driver.driver
import net.corda.testing.node.User

fun main(args: Array<String>) {
    // No permissions required as we are not invoking flows.
    val user = User("user1", "test", permissions = setOf("ALL"))
    driver(DriverParameters(
            waitForAllNodesToFinish = true,
            startNodesInProcess = true,
            extraCordappPackagesToScan = listOf("com.template"))) {
        startNode(providedName = CordaX500Name("PartyA", "London", "GB"), rpcUsers = listOf(user)).getOrThrow()
        startNode(providedName = CordaX500Name("PartyB", "New York", "US"), rpcUsers = listOf(user)).getOrThrow()
    }
}