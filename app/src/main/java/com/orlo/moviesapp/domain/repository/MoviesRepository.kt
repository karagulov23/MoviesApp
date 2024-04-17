package com.orlo.moviesapp.domain.repository

import com.orlo.moviesapp.domain.model.Movie
import com.orlo.moviesapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>>

    suspend fun getMovie(id: Int): Flow<Resource<Movie>>

}