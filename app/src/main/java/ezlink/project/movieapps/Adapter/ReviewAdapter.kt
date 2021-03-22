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
import ezlink.project.movieapps.Model.ReviewItem
import ezlink.project.movieapps.Movie
import ezlink.project.movieapps.R

class ReviewAdapter(val context: Context) : RecyclerView.Adapter<ReviewAdapter.MyViewHolder>() {

    private val reviews : MutableList<ReviewItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.home_review_item, parent, false))
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(reviews[position])
    }

    fun setReview(data: List<ReviewItem>){
        reviews.clear()
        reviews.addAll(data)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(item: View) : RecyclerView.ViewHolder(item){

        val name: TextView = item.findViewById(R.id.name)
        val username: TextView = item.findViewById(R.id.username)
        val rating: TextView = item.findViewById(R.id.rating)
        val cardview : CardView = item.findViewById(R.id.cardview)
        val avatar_path : ImageView = item.findViewById(R.id.avatar_path)

        fun bind(all: ReviewItem){
            name.text = all.authorDetails?.name
            username.text = all.authorDetails?.username
            rating.text = all.authorDetails?.rating.toString()+" / 10.0"

            Glide.with(context).load(ApiConfig.URL_IMAGE+""+all.authorDetails?.avatar_path).into(avatar_path)

//            cardview.setOnClickListener(View.OnClickListener {
//
//                val intent = Intent(context, Movie::class.java)
//                intent.putExtra("id", all.id)
//                context.startActivity(intent)
//            })
        }
    }
}