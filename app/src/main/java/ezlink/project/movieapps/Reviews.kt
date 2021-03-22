package ezlink.project.movieapps

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ezlink.project.movieapps.API.ApiConfig
import ezlink.project.movieapps.API.ApiService
import ezlink.project.movieapps.Adapter.ReviewAdapter
import ezlink.project.movieapps.Model.ReviewItem
import ezlink.project.movieapps.Model.Review
import kotlinx.android.synthetic.main.home_detail.*
import kotlinx.android.synthetic.main.progress.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Reviews : AppCompatActivity() {
    lateinit var reviewAdapter: ReviewAdapter
    val lm = LinearLayoutManager(this)
    var mQuestions: MutableList<ReviewItem> = ArrayList()

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
        reviewAdapter = ReviewAdapter(this)
        recycler_view.adapter = reviewAdapter

        val bundle : Bundle?=intent.extras
        if (bundle!=null){
            movieid = bundle.getInt("id") // 1
        }

        progress.visibility = View.VISIBLE
        getList()
    }

    fun getList(){
        Service.review(movieid, page, ApiConfig.Api_Key).enqueue(object : Callback<Review> {
            override fun onFailure(call: Call<Review>, t: Throwable) {
            }

            override fun onResponse(call: Call<Review>, response: Response<Review>) {
//                Log.d("BISMILLAH", "Total Questions: " + response.body())
                val questions = response.body()
                if (questions != null) {
//                    Log.d("BISMILLAH", "" + questions.movies)
                    progress.visibility = View.GONE

                    page_max = questions.totalPages!!

                    mQuestions.addAll(questions.reviews!!)
                    reviewAdapter!!.setReview(mQuestions)
                }
            }
        })

        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if(dy > 0){
                    var vItem = lm.childCount
                    var lItem = lm.findFirstCompletelyVisibleItemPosition()
                    var count = reviewAdapter.itemCount

                    if(!recycleprogress && page <= page_max){
                        if(vItem+lItem >= count){
                            page++
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

        Service.review(movieid, page, ApiConfig.Api_Key).enqueue(object : Callback<Review> {
            override fun onFailure(call: Call<Review>, t: Throwable) {
            }

            override fun onResponse(call: Call<Review>, response: Response<Review>) {
//                Log.d("BISMILLAH", "Total Questions: " + response.body())
                val questions = response.body()
                if (questions != null) {
//                    Log.d("BISMILLAH", "" + questions.movies)

                    Handler().postDelayed({
                        recycleprogress = false
                        recycler_progress.visibility = View.GONE
                        mQuestions.addAll(questions.reviews!!)
                        reviewAdapter!!.setReview(mQuestions)
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