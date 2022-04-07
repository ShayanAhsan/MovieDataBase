package com.example.moviedatabase.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [MoviesModel::class],version = 1)
abstract class MoviesDatabase:RoomDatabase() {

    abstract fun moviesDAO(): MoviesDAO
}