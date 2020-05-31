package com.stardeux.upprime.rate.usecase

import com.stardeux.upprime.rate.repository.RateAppRepository

class RateAppUseCase(private val rateAppRepository: RateAppRepository) {

    private var favorableActions = 0

    fun getRateAppAnswer(): RateAppAnswer? {
        return rateAppRepository.getRateAppAnswer()
    }

    fun setRateAppAnswer(rateAppAnswer: RateAppAnswer) {
        rateAppRepository.setRateAppAnswer(rateAppAnswer)
    }

    fun incrementFavorableAction() {
        favorableActions++
    }

    fun canDisplayRateApp(): Boolean {
        return favorableActions == 3 && (getRateAppAnswer() == RateAppAnswer.NOT_NOW || getRateAppAnswer() == null)
    }
}