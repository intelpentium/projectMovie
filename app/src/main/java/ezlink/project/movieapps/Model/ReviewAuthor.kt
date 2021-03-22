package ezlink.project.movieapps.Model

import com.google.gson.annotations.SerializedName

class ReviewAuthor (

        @field:SerializedName("avatar_path")
        val avatar_path: String? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("rating")
        val rating: Double? = null,

        @field:SerializedName("username")
        val username: String? = null
)