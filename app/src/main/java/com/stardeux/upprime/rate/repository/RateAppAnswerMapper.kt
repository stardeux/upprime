package com.stardeux.upprime.rate.repository

import com.stardeux.upprime.rate.usecase.RateAppAnswer

class RateAppAnswerMapper {

    fun mapToRateAppAnswer(rateAppAnswerData: RateAppAnswerData): RateAppAnswer {
        return when(rateAppAnswerData) {
            RateAppAnswerData.YES -> RateAppAnswer.YES
            RateAppAnswerData.NEVER -> RateAppAnswer.NEVER
            RateAppAnswerData.NOT_NOW -> RateAppAnswer.NOT_NOW
        }
    }

    fun mapToRateAppAnswerData(rateAppAnswer: RateAppAnswer): RateAppAnswerData {
        return when(rateAppAnswer) {
            RateAppAnswer.YES -> RateAppAnswerData.YES
            RateAppAnswer.NEVER -> RateAppAnswerData.NEVER
            RateAppAnswer.NOT_NOW -> RateAppAnswerData.NOT_NOW
        }
    }

}