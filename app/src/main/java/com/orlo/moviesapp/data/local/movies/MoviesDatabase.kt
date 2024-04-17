package com.orlo.moviesapp.data.local.movies

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MovieEntity::class],
    version = 1,
)

abstract class MoviesDatabase: RoomDatabase() {

    abstract val movieDao: MovieDao

}