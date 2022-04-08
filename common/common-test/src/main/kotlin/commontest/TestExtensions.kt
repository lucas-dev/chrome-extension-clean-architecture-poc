package commontest

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise

fun runTest(block: suspend () -> Unit): dynamic = GlobalScope.promise { block() }
