package com.stardeux.upprime.rate.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.stardeux.upprime.rate.usecase.RateAppAnswer

class RateAppRepository(
    private val sharedPreferences: SharedPreferences,
    private val rateAppAnswerMapper: RateAppAnswerMapper
) {

    fun getRateAppAnswer(): RateAppAnswer? {
        val rateAppData = sharedPreferences.getString(RATE_APP_ANSWER_KEY, null)?.let {
            Gson().fromJson(it, RateAppAnswerData::class.java)
        }

        return rateAppData?.let { rateAppAnswerMapper.mapToRateAppAnswer(it) }
    }

    fun setRateAppAnswer(rateAppAnswer: RateAppAnswer) {
        val rateAppStringValue =
            Gson().toJson(rateAppAnswerMapper.mapToRateAppAnswerData(rateAppAnswer))
        sharedPreferences.edit { putString(RATE_APP_ANSWER_KEY, rateAppStringValue) }
    }

    companion object {
        private const val RATE_APP_ANSWER_KEY = "rate_app_answer_key"
    }

}