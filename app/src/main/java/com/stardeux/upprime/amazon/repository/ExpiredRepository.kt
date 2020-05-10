package com.stardeux.upprime.amazon.repository

import com.stardeux.upprime.amazon.repository.api.ExpiredApi

class ExpiredRepository (private val expiredApi: ExpiredApi) {

    suspend fun expired() {

    }
}