package ezlink.project.movieapps

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ezlink.project.movieapps.API.ApiConfig
import ezlink.project.movieapps.API.ApiService
import ezlink.project.movieapps.Adapter.GenreAdapter
import ezlink.project.movieapps.Model.Genre
import ezlink.project.movieapps.Model.GenreItem
import kotlinx.android.synthetic.main.home.*
import kotlinx.android.synthetic.main.progress.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : AppCompatActivity() {

    private var mQuestions: MutableList<GenreItem> = ArrayList()
    var genreAdapter: GenreAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        setSupportActionBar(toolbar)
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        recycler_view.layoutManager = LinearLayoutManager(this)
        genreAdapter = GenreAdapter(this)
        recycler_view.adapter = genreAdapter

        progress.visibility = View.VISIBLE
        getList()
    }

    fun getList(){
        val Service = ApiConfig.getServer().create(ApiService::class.java)
        Service.genre(ApiConfig.Api_Key).enqueue(object : Callback<Genre>{
            override fun onFailure(call: Call<Genre>, t: Throwable) {
                Log.d("BISMILLAH", "" + t.message.toString())
            }

            override fun onResponse(call: Call<Genre>, response: Response<Genre>) {
//                Log.d("BISMILLAH", "Total Questions: " + response.body()!!.genres!!.size)
                val questions = response.body()
                if (questions != null) {
//                    Log.d("BISMILLAH", "" + questions.genres)
                    progress.visibility = View.GONE

                    mQuestions.addAll(questions.genres!!)
                    genreAdapter!!.setGenre(mQuestions)
                }

            }

        })
    }

//    fun addMore(){
//        for (i in 0..6){
//
//        }
//    }
}
