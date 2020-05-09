package com.stardeux.upprime.tmdb.movie.repository.model

import com.google.gson.annotations.SerializedName
import com.stardeux.upprime.tmdb.common.model.GenreResponse
import com.stardeux.upprime.tmdb.common.model.ProductionCountriesResponse

data class TmdbMovieDetailsResponse(
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("poster_path") val posterUrl: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("genres") val genres: List<GenreResponse>?,
    @SerializedName("production_countries") val productionCountries: List<ProductionCountriesResponse>?,
    @SerializedName("vote_average") val voteAverage: Float?
)