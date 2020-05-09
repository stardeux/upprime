package com.stardeux.upprime.tmdb.find.usecase.error

class SeriesNotFoundException(imdbSeriesId: String) : Exception("Series not found $imdbSeriesId")