package com.orlo.moviesapp.presentation.screens.detail

import com.orlo.moviesapp.domain.model.Movie

data class DetailScreenState(
    val isLoading: Boolean = false,
    val movie: Movie? = null
)