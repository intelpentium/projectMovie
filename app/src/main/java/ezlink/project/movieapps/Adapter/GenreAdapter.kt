package ezlink.project.movieapps.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ezlink.project.movieapps.HomeDetail
import ezlink.project.movieapps.MainActivity
import ezlink.project.movieapps.Model.GenreItem
import ezlink.project.movieapps.R
import ezlink.project.movieapps.Util.DataSession

class GenreAdapter(val context: Context) : RecyclerView.Adapter<GenreAdapter.MyViewHolder>() {

    private val genres : MutableList<GenreItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item, parent, false))
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    fun setGenre(data: List<GenreItem>){
        genres.clear()
        genres.addAll(data)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(item: View) :RecyclerView.ViewHolder(item){

        val name: TextView = item.findViewById(R.id.name)
        val cardview : CardView = item.findViewById(R.id.cardview)

        fun bind(all: GenreItem){
            name.text = all.name
            cardview.setOnClickListener(View.OnClickListener {

                val intent = Intent(context, HomeDetail::class.java)
                intent.putExtra("id", all.id)
                context.startActivity(intent)
            })
        }
    }
}