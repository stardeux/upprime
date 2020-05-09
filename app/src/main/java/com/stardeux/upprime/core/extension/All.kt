package com.stardeux.upprime.core.extension

import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

public inline fun <T> T.takeIf(predicate: (T) -> Boolean): T? {
    return if (predicate(this)) this else null
}