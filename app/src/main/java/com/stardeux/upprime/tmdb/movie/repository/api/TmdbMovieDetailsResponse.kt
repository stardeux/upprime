package com.stardeux.upprime.tmdb.movie.repository.api

import com.google.gson.annotations.SerializedName
import com.stardeux.upprime.tmdb.common.model.network.GenreResponse
import com.stardeux.upprime.tmdb.common.model.network.ProductionCountryResponse

data class TmdbMovieDetailsResponse(
    @SerializedName("id") val tmdbId: String?,
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("original_title") val original_title: String?,
    @SerializedName("poster_path") val posterUrl: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("genres") val genres: List<GenreResponse>?,
    @SerializedName("production_countries") val productionCountries: List<ProductionCountryResponse>?,
    @SerializedName("vote_average") val voteAverage: Float?,
    @SerializedName("vote_count") val voteCount: Int?,
    @SerializedName("overview") val synopsis: String?,
    @SerializedName("backdrop_path") val backdropPath: String?
)