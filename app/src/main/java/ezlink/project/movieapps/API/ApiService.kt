package ezlink.project.movieapps.API

import ezlink.project.movieapps.Model.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // list genre
    @GET("genre/movie/list")
    fun genre(@Query("api_key") apikey: String): Call<Genre>

    // list movie
    @GET("discover/movie")
    fun movie(@Query("with_genres") movieid: Int?,
              @Query("page") page: Int?,
              @Query("api_key") apikey: String): Call<Movie>

    // movie detail
    @GET("movie/{id}")
    fun moviedetail(@Path("id") movieid: Int?,
                    @Query("api_key") apikey: String): Call<MovieItem>

    // movie detail
    @GET("movie/{id}/reviews")
    fun review(@Path("id") movieid: Int?,
               @Query("page") page: Int?,
               @Query("api_key") apikey: String): Call<Review>

    // youtube detail
    @GET("movie/{id}/videos")
    fun youtube(@Path("id") movieid: Int?,
                    @Query("api_key") apikey: String): Call<Youtube>
}