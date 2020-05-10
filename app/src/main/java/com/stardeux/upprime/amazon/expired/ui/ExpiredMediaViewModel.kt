package com.stardeux.upprime.amazon.expired.ui

import androidx.lifecycle.ViewModel
import com.stardeux.upprime.amazon.expired.usecase.GetExpiredMediaUseCase
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase

class ExpiredMediaViewModel(
    private val getExpiredMediaUseCase: GetExpiredMediaUseCase,
    private val getImdbMovieDetailsUseCase: GetImdbMovieDetailsUseCase,
    private val getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase
) : ViewModel() {

}