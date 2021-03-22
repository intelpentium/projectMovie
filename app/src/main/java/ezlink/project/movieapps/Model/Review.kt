package ezlink.project.movieapps.Model

import com.google.gson.annotations.SerializedName

class Review (

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("page")
        val page: Int? = null,

        @field:SerializedName("total_pages")
        val totalPages: Int? = null,

        @field:SerializedName("results")
        val reviews: List<ReviewItem>? = null, // perhatikan List<ReviewItem?>?

        @field:SerializedName("total_results")
        val totalResults: Int? = null
)