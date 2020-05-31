package com.stardeux.upprime.rate.usecase

import com.stardeux.upprime.rate.repository.RateAppRepository

class RateAppUseCase(private val rateAppRepository: RateAppRepository) {

    fun getRateAppAnswer(): RateAppAnswer? {
        return rateAppRepository.getRateAppAnswer()
    }

    fun setRateAppAnswer(rateAppAnswer: RateAppAnswer) {
        rateAppRepository.setRateAppAnswer(rateAppAnswer)
    }

    fun canDisplayRateApp(): Boolean {
        return getRateAppAnswer() == RateAppAnswer.NOT_NOW || getRateAppAnswer() == null
    }
}