package com.orlo.moviesapp.di

import com.orlo.moviesapp.data.repository.MovieListRepositoryImpl
import com.orlo.moviesapp.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieListRepositoryImpl,
    ): MoviesRepository

}