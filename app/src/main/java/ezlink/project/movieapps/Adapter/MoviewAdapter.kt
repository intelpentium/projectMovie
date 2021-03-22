package ezlink.project.movieapps.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ezlink.project.movieapps.API.ApiConfig
import ezlink.project.movieapps.MainActivity
import ezlink.project.movieapps.Model.MovieItem
import ezlink.project.movieapps.Movie
import ezlink.project.movieapps.R
import ezlink.project.movieapps.Reviews
import kotlinx.android.synthetic.main.home_detail.*

class MoviewAdapter(val context: Context) : RecyclerView.Adapter<MoviewAdapter.MyViewHolder>() {

    private val moviews : MutableList<MovieItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.home_detail_item, parent, false))
    }

    override fun getItemCount(): Int {
        return moviews.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(moviews[position])
    }

    fun setMovie(data: List<MovieItem>){
        moviews.clear()
        moviews.addAll(data)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(item: View) : RecyclerView.ViewHolder(item){

        val title: TextView = item.findViewById(R.id.title)
        val release_date: TextView = item.findViewById(R.id.release_date)
        val overview: TextView = item.findViewById(R.id.overview)
        val vote_count: TextView = item.findViewById(R.id.vote_count)
        val vote_average: TextView = item.findViewById(R.id.vote_average)
        val cardview : CardView = item.findViewById(R.id.cardview)
        val poster_path : ImageView = item.findViewById(R.id.poster_path)

        fun bind(all: MovieItem){
            title.text = all.title
            release_date.text = all.release_date
            overview.text = all.overview
            vote_count.text = "Total Rating : "+all.vote_average.toString()
            vote_average.text = "Reviews :"+all.vote_count.toString()

            Glide.with(context).load(ApiConfig.URL_IMAGE+""+all.posterPath).into(poster_path)

            cardview.setOnClickListener(View.OnClickListener {
                val intent = Intent(context, Movie::class.java)
                intent.putExtra("id", all.id)
                context.startActivity(intent)
            })

            vote_average.setOnClickListener({
                val intent = Intent(context, Reviews::class.java)
                intent.putExtra("id", all.id)
                context.startActivity(intent)
            })
        }
    }
}