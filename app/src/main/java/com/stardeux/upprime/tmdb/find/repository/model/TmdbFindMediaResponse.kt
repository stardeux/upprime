package com.stardeux.upprime.tmdb.find.repository.model

import com.google.gson.annotations.SerializedName

data class TmdbFindMediaResponse(
    @SerializedName("movie_results") val moviesResults: List<TmdbFindMovieResponse>?,
    @SerializedName("tv_results") val seriesResults: List<TmdbFindSeriesResponse>?
)