package com.stardeux.upprime.tmdb.find.repository.model

import com.google.gson.annotations.SerializedName

data class FindMediaResponse(
    @SerializedName("movie_results") val moviesResults: List<FindMovieResponse>?,
    @SerializedName("tv_results") val seriesResults: List<FindSeriesResponse>?
)