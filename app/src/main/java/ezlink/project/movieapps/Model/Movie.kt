package ezlink.project.movieapps.Model

import com.google.gson.annotations.SerializedName

class Movie (
    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val total_pages: Int? = null,

    @field:SerializedName("results")
    val movies: List<MovieItem>? = null, // perhatikan List<MovieItem?>?

    @field:SerializedName("total_results")
    val total_results: Int? = null
)