package com.stardeux.upprime.core.extension

public inline fun <T> T.takeIf(predicate: (T) -> Boolean): T? {
    return if (predicate(this)) this else null
}

val <T> T.exhaustive: T
    get() = this