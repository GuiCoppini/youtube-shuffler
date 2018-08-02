package shuffler.youtubeshuffler.payload

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

data class SongResponse(
        @JsonProperty("current_time")
        val currentTime: Double,

        @JsonProperty("song_name")
        val songName: String
)

data class SongRequest(
        @NotNull
        @JsonProperty("song_name")
        val name: String
)