package com.stardeux.upprime.core.extension

import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import java.util.concurrent.Executors

fun <T> createAsyncDifferConfig(diffUtil: DiffUtil.ItemCallback<T>): AsyncDifferConfig<T> {
    return AsyncDifferConfig.Builder<T>(diffUtil)
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
}