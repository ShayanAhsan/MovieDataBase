package com.example.moviedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.room.Room
import com.example.moviedatabase.api.MoviesDetailsModel
import com.example.moviedatabase.api.MyRetrofit
import com.example.moviedatabase.database.MoviesDatabase
import com.example.moviedatabase.database.MoviesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchForMovies : AppCompatActivity() {


    private lateinit var editTextSearchForMovies: EditText
    private lateinit var textView2: TextView
    private lateinit var button: ImageButton
    private lateinit var addMoviesToDBButton: Button



    private lateinit var tvTitle:TextView
    private lateinit var tvYear:TextView
    private lateinit var tvRated:TextView
    private lateinit var tvReleased:TextView
    private lateinit var tvRunTime:TextView
    private lateinit var tvGenre:TextView
    private lateinit var tvDirector:TextView
    private lateinit var tvWriter:TextView
    private lateinit var tvActors:TextView
    private lateinit var tvPlots:TextView
    private lateinit var scrollView:ScrollView
    private lateinit var database: MoviesDatabase




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_for_movies)


        initializeView()

        setOnClickListener()

    }

    private fun setOnClickListener() {
        addMoviesToDBButton.setOnClickListener {
            addToTheDataBase()
        }

        button.setOnClickListener {
            callApi()
        }

    }

    private fun initializeView() {
        editTextSearchForMovies = findViewById(R.id.EditTextSearchForMovies)
        button = findViewById(R.id.button2)
        tvTitle = findViewById(R.id.tvTitle)
        tvYear = findViewById(R.id.tvYear)
        tvRated = findViewById(R.id.tvRated)
        tvReleased = findViewById(R.id.tvReleased)
        tvRunTime = findViewById(R.id.tvRuntime)
        tvGenre = findViewById(R.id.tvGenre)
        tvDirector = findViewById(R.id.tvDirector)
        tvWriter = findViewById(R.id.tvWriter)
        tvActors = findViewById(R.id.tvActors)
        tvPlots = findViewById(R.id.tvPlots)
        scrollView = findViewById(R.id.ScrollView)
        addMoviesToDBButton  =findViewById(R.id.ButtonAddMoviesToDatabase)

    }

    private fun callApi() {

        MyRetrofit.getClint().searchMovie(editTextSearchForMovies.text.toString(),"7ee3ed9")
            .enqueue(object : Callback<MoviesDetailsModel>{
                override fun onResponse(call: Call<MoviesDetailsModel>, response: Response<MoviesDetailsModel>) {
                    if (response.isSuccessful){
                        var condition = response.body()?.Response
                        if ( condition == true){
                        scrollView.visibility  = View.VISIBLE
                        tvTitle.text = response.body()?.Title
                        tvYear.text = response.body()?.Year
                        tvRated.text = response.body()?.Rated
                        tvReleased.text = response.body()?.Released
                        tvRunTime.text = response.body()?.Runtime
                        tvGenre.text = response.body()?.Genre
                        tvDirector.text = response.body()?.Director
                        tvWriter.text = response.body()?.Writer
                        tvActors.text = response.body()?.Actors
                        tvPlots.text = response.body()?.Plot
                        }else{
                            tvTitle.text = ""
                            tvYear.text = ""
                            tvRated.text = ""
                            tvReleased.text = ""
                            tvRunTime.text = ""
                            tvGenre.text = ""
                            tvDirector.text = ""
                            tvWriter.text = ""
                            tvActors.text = ""
                            tvPlots.text = ""
                            scrollView.visibility = View.GONE
                            Toast.makeText(this@SearchForMovies,"Movie not Found",Toast.LENGTH_LONG).show()
                        }


//                        Toast.makeText(this@SearchForMovies,list.toString(),Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this@SearchForMovies,"Some Thing Went Wrong",Toast.LENGTH_LONG).show()


                    }

                }

                override fun onFailure(call: Call<MoviesDetailsModel>, t: Throwable) {
                    Toast.makeText(this@SearchForMovies,"Failed",Toast.LENGTH_LONG).show()
                }

            })

    }

    private fun addToTheDataBase(){
        if (tvTitle.text.toString().isEmpty()&&tvYear.text.toString().isEmpty()&&tvRated.text.toString().isEmpty()&&tvReleased.text.toString().isEmpty()&&
            tvRunTime.text.toString().isEmpty()&&tvGenre.text.toString().isEmpty()&&tvDirector.text.toString().isEmpty()&&tvWriter.text.toString().isEmpty()&&
            tvActors.text.toString().isEmpty()&&tvPlots.text.toString().isEmpty())
        {
            Toast.makeText(this,"Movie Not Found",Toast.LENGTH_LONG).show()
        }else{


            database = Room.databaseBuilder(applicationContext,MoviesDatabase::class.java,"MoviesDB").allowMainThreadQueries().build()

            database.moviesDAO().insertMovies(
                MoviesModel( tvTitle.text.toString(), tvYear.text.toString(), tvRated.text.toString(),
                    tvReleased.text.toString(),tvRunTime.text.toString(), tvGenre.text.toString(),tvDirector.text.toString(),tvWriter.text.toString(),
                    tvActors.text.toString(),tvPlots.text.toString()
                ))

            val data = database.moviesDAO().getMovies("Chris")
            Toast.makeText(this,"Movies Added to The DataBase",Toast.LENGTH_LONG).show()
            Toast.makeText(this,data.toString(),Toast.LENGTH_LONG).show()
        }



    }
}