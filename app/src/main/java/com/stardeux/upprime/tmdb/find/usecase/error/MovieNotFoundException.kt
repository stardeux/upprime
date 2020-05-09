package com.stardeux.upprime.tmdb.find.usecase.error

class MovieNotFoundException(imdbMovieId: String) : Exception("Movie not found $imdbMovieId")