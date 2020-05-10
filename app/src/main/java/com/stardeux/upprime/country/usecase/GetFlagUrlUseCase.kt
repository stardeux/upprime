package com.stardeux.upprime.country.usecase

import android.net.Uri
import java.util.*

class GetFlagUrlUseCase {

    //https://www.countryflags.io/be/flat/64.png
    operator fun invoke(locale: Locale): Uri {
        return Uri.Builder()
            .scheme("https")
            .authority("www.countryflags.io")
            .appendPath(locale.country)
            .appendPath("flat")
            .appendPath("64.png")
            .build()
    }

}