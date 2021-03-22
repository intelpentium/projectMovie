package ezlink.project.movieapps

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import ezlink.project.movieapps.API.ApiConfig
import ezlink.project.movieapps.API.ApiService
import ezlink.project.movieapps.Model.Youtube
import ezlink.project.movieapps.Model.YoutubeItem
import kotlinx.android.synthetic.main.home_movie.*
import kotlinx.android.synthetic.main.progress.*
import kotlinx.android.synthetic.main.youtube.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Youtube : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    var movieid: Int?=null
    var VIDEO_ID: String = "";

    var mQuestions: MutableList<YoutubeItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.youtube)

        val bundle :Bundle ?=intent.extras
        if (bundle!=null){
            movieid = bundle.getInt("id") // 1
        }

        progress.visibility = View.VISIBLE
        getData()
    }

    fun getData(){
        val Service = ApiConfig.getServer().create(ApiService::class.java)
        Service.youtube(movieid, ApiConfig.Api_Key).enqueue(object : Callback<Youtube> {
            override fun onFailure(call: Call<Youtube>, t: Throwable) {

            }

            override fun onResponse(call: Call<Youtube>, response: Response<Youtube>) {
                val request = response.body()
                if(request != null){

                    progress.visibility = View.GONE
                    mQuestions.addAll(request.results!!)

                    VIDEO_ID = mQuestions.get(0).key!!

                    yt_pv.initialize(ApiConfig.APi_key_youtube, this@Youtube)
                }
            }

        })
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        if (!wasRestored) {
            player?.loadVideo(VIDEO_ID);
        }
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
    }

}