package ezlink.project.movieapps.API

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {
    val Api_Key = "18bca0aa769a8eec5d4b3a7dedb00f82"
    val URL_IMAGE = "https://image.tmdb.org/t/p/w500/"
    val APi_key_youtube = "AIzaSyC9L_a21XOKQZuycNP8WMFNKvqyJfXu1Fk"
    val Session = "session"

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    fun getServer(): Retrofit{
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }
}