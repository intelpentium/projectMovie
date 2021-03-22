package ezlink.project.movieapps

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ezlink.project.movieapps.API.ApiConfig
import ezlink.project.movieapps.API.ApiService
import ezlink.project.movieapps.Adapter.MoviewAdapter
import ezlink.project.movieapps.Model.Movie
import ezlink.project.movieapps.Model.MovieItem
import kotlinx.android.synthetic.main.home_detail.*
import kotlinx.android.synthetic.main.progress.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeDetail : AppCompatActivity() {
    lateinit var movieAdapter: MoviewAdapter
    val lm = LinearLayoutManager(this)
    var mQuestions: MutableList<MovieItem> = ArrayList()

    var movieid: Int?=null
    var page: Int = 1
    var page_max: Int = 0
    var recycleprogress = false

    val Service = ApiConfig.getServer().create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_detail)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        recycler_view.layoutManager = lm
        movieAdapter = MoviewAdapter(this)
        recycler_view.adapter = movieAdapter

        val bundle :Bundle ?=intent.extras
        if (bundle!=null){
            movieid = bundle.getInt("id") // 1
        }

        progress.visibility = View.VISIBLE
        getList()
    }

    fun getList(){
        Service.movie(movieid, page, ApiConfig.Api_Key).enqueue(object : Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                val questions = response.body()
                if (questions != null) {
//                    Log.d("BISMILLAH", "" + questions.movies)

                    progress.visibility = View.GONE

                    page_max = questions.total_pages!!

                    mQuestions.addAll(questions.movies!!)
                    movieAdapter!!.setMovie(mQuestions)
                }
            }
        })

        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if(dy > 0){
                    var vItem = lm.childCount
                    var lItem = lm.findFirstCompletelyVisibleItemPosition()
                    var count = movieAdapter.itemCount

                    if(!recycleprogress && page <= page_max){
                        if(vItem+lItem >= count){
                            page++

                            Log.d("BISMILLAH", page.toString())
                            loadMore()
                        }
                    }
                }

                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    fun loadMore(){
        recycleprogress = true
        recycler_progress.visibility = View.VISIBLE

        Service.movie(movieid, page, ApiConfig.Api_Key).enqueue(object : Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
//                Log.d("BISMILLAH", "Total Questions: " + response.body())
                val questions = response.body()
                if (questions != null) {
//                    Log.d("BISMILLAH", "" + questions.movies)

                    Handler().postDelayed({
                        recycleprogress = false
                        recycler_progress.visibility = View.GONE
                        mQuestions.addAll(questions.movies!!)
                        movieAdapter!!.setMovie(mQuestions)
                    }, 5000)

                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}