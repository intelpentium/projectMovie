package ezlink.project.movieapps.Model

import com.google.gson.annotations.SerializedName

class Genre {
    @field:SerializedName("genres")
    val genres: List<GenreItem>? = null // perhatikan List<GenreItem?>?
}