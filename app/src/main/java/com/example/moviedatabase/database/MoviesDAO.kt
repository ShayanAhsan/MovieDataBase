package com.example.moviedatabase.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.selects.select


@Dao
interface MoviesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(model: MoviesModel)


    @Query("SELECT * FROM `Movies Details` Where Actors Like  '%' || :ActorName || '%'")
   fun getMovies(ActorName:String):List<MoviesModel>
}