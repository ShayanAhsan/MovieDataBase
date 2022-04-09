package com.example.moviedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.moviedatabase.database.MoviesDatabase
import com.example.moviedatabase.database.MoviesModel

class SearchMoviesByActorName : AppCompatActivity() {

    private lateinit var editTextSearchForActors:EditText
    private lateinit var database: MoviesDatabase

    private lateinit var mList: List<MoviesModel>
    private lateinit var recyclerView: RecyclerView

    private lateinit var searchButton: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movies_by_actor_name)


        initializeView()

        setOnClickListeners()



    }

    private fun setOnClickListeners() {
        searchButton.setOnClickListener {

            getDataFromDatabase()
        }

    }

    private fun initializeView() {


        recyclerView  = findViewById(R.id.RecyclerViewForActorMoviesList)
        editTextSearchForActors = findViewById(R.id.EditTextSearchForActors)
        searchButton = findViewById(R.id.SearchButtonInMoviesByActorName)

    }

    private fun getDataFromDatabase(){
        database = Room.databaseBuilder(applicationContext, MoviesDatabase::class.java,"MoviesDB").allowMainThreadQueries().build()

        mList =  database.moviesDAO().getMovies(editTextSearchForActors.text.toString())
        if (editTextSearchForActors.text.toString().isNotEmpty()) {


            recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            recyclerView.adapter = AdapterForRecyclerView(this, mList as ArrayList<MoviesModel>)

        }


        else{
            Toast.makeText(this,"Please Enter Actor Name", Toast.LENGTH_LONG).show()

        }



    }

}