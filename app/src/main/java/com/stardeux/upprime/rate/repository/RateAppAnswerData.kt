package com.stardeux.upprime.rate.repository

import com.google.gson.annotations.SerializedName

enum class RateAppAnswerData {
    @SerializedName("yes")
    YES,

    @SerializedName("no")
    NO,

    @SerializedName("not_now")
    NOT_NOW
}