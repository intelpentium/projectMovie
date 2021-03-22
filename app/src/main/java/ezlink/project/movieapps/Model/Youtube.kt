package ezlink.project.movieapps.Model

import com.google.gson.annotations.SerializedName

data class Youtube(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("results")
	val results: List<YoutubeItem>? = null
)
