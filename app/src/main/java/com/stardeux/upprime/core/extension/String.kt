package com.stardeux.upprime.core.extension

fun spaceSeparator(vararg string: String?): String {
    return string.joinToString(separator = " ")
}