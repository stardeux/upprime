package com.stardeux.upprime.core.extension

inline fun <reified E : Enum<E>> enumFromName(name: String, default: E? = null): E? =
    enumValues<E>().find { it.name.toLowerCase() == name.toLowerCase() } ?: default

inline fun <reified E : Enum<E>> enumFromOrdinal(ordinal: Int, default: E? = null): E? =
    enumValues<E>().find { it.ordinal == ordinal } ?: default
