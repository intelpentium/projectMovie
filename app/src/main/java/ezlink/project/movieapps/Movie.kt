package ezlink.project.movieapps

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import ezlink.project.movieapps.API.ApiConfig
import ezlink.project.movieapps.API.ApiService
import ezlink.project.movieapps.Model.MovieItem
import kotlinx.android.synthetic.main.home_movie.*
import kotlinx.android.synthetic.main.progress.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Movie : AppCompatActivity() {

    var movieid: Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_movie)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val bundle :Bundle ?=intent.extras
        if (bundle!=null){
            movieid = bundle.getInt("id") // 1
        }

        progress.visibility = View.VISIBLE
        getData()

        vote_average.setOnClickListener({

            val intent = Intent(this@Movie, Reviews::class.java)
            intent.putExtra("id", movieid)
            startActivity(intent)
        })

        lineedit.setOnClickListener({

            val intent = Intent(this@Movie, Youtube::class.java)
            intent.putExtra("id", movieid)
            startActivity(intent)
        })
    }

    fun getData(){
        val Service = ApiConfig.getServer().create(ApiService::class.java)
        Service.moviedetail(movieid, ApiConfig.Api_Key).enqueue(object : Callback<MovieItem>{
            override fun onFailure(call: Call<MovieItem>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieItem>, response: Response<MovieItem>) {
                val request = response.body()
                if(request != null){

                    progress.visibility = View.GONE
                    Glide.with(this@Movie).load(ApiConfig.URL_IMAGE+""+request.posterPath).into(poster_path)

                    judul.text = request.title
                    release_date.text = "Release Date : "+request.release_date
                    vote_count.text = "Total Reviews : "+request.vote_count.toString()
                    vote_average.text = request.vote_average.toString()+" / 10.0"
                    overview.text = request.overview.toString()
                }
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}