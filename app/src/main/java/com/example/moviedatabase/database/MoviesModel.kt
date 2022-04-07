package com.example.moviedatabase.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Movies Details")
data class MoviesModel(

    @PrimaryKey(autoGenerate = false)
    var Title:String,
    var Year:String,
    var Rated:String,
    var Released:String,
    var Runtime:String,
    var Genre:String,
    var Director:String,
    var Writer:String,
    var Actors:String,
    var Plot:String
)
