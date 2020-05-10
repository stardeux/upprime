package com.stardeux.upprime.amazon.expired.repository

import com.stardeux.upprime.amazon.expired.repository.api.ExpiredApi

class ExpiredRepository (private val expiredApi: ExpiredApi) {

    suspend fun expired() {

    }
}