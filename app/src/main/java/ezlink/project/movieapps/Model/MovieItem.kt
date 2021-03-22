package ezlink.project.movieapps.Model

import com.google.gson.annotations.SerializedName

class MovieItem (

        @field:SerializedName("overview")
        val overview: String? = null,

        @field:SerializedName("title")
        val title: String? = null,

        @field:SerializedName("poster_path")
        val posterPath: String? = null,

        @field:SerializedName("release_date")
        val release_date: String? = null,

        @field:SerializedName("vote_average")
        val vote_average: Double? = null,

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("vote_count")
        val vote_count: Int? = null,

        @field:SerializedName("revenue")
        val revenue: Int? = null
)