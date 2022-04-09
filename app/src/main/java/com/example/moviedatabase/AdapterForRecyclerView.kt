package com.example.moviedatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedatabase.database.MoviesModel

class AdapterForRecyclerView(private var context: SearchMoviesByActorName,private var list: ArrayList<MoviesModel>):RecyclerView.Adapter<AdapterForRecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerviewlayout,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        holder.tvSearchForActorTitle.text=model.Title
        holder.tvSearchForActorYear.text=model.Year
        holder.tvSearchForActorRated.text=model.Rated
        holder.tvSearchForActorReleased.text=model.Released
        holder.tvSearchForActorRuntime.text=model.Runtime
        holder.tvSearchForActorGenre.text=model.Genre
        holder.tvSearchForActorDirector.text=model.Director
        holder.tvSearchForActorWriter.text=model.Writer
        holder.tvSearchForActorActors.text=model.Actors
        holder.tvSearchForActorPlots.text=model.Plot

    }

    override fun getItemCount(): Int {
       return list.size
    }
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var tvSearchForActorTitle: TextView = itemView.findViewById(R.id.tvSearchForActorTitle)
        var tvSearchForActorYear: TextView = itemView. findViewById(R.id.tvSearchForActorYear)
        var tvSearchForActorRated: TextView = itemView.findViewById(R.id.tvSearchForActorRated)
        var tvSearchForActorReleased: TextView = itemView.findViewById(R.id.tvSearchForActorReleased)
        var tvSearchForActorRuntime: TextView = itemView. findViewById(R.id.tvSearchForActorRuntime)
        var tvSearchForActorGenre: TextView = itemView.findViewById(R.id.tvSearchForActorGenre)
        var tvSearchForActorDirector: TextView = itemView.findViewById(R.id.tvSearchForActorDirector)
        var tvSearchForActorWriter: TextView = itemView.findViewById(R.id.tvSearchForActorWriter)
        var tvSearchForActorActors: TextView = itemView.findViewById(R.id.tvSearchForActorActors)
        var tvSearchForActorPlots: TextView = itemView.findViewById(R.id.tvSearchForActorPlots)

    }
}