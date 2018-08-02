package shuffler.youtubeshuffler.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import shuffler.youtubeshuffler.payload.SongRequest
import shuffler.youtubeshuffler.payload.SongResponse
import shuffler.youtubeshuffler.service.SongManager
import javax.servlet.http.HttpServletResponse

@CrossOrigin
@RestController
class TestController {


    @Autowired
    lateinit var songManager: SongManager

    @RequestMapping(path = ["/get-song"])
    fun getSong(response: HttpServletResponse) {
        if(StringUtils.isEmpty(songManager.actualSongName)) {
            println("There is no song set!")
            throw RuntimeException("No song set!")
        }
        println("Uploading MP3")
        val out = response.outputStream
        response.contentType = "audio/mpeg"
        val fileIn = songManager.getMp3ByName(songManager.actualSongName!!)
        val bytes = fileIn!!.readBytes()

        out.write(bytes)

        println("MP3 uploaded")
    }

    @RequestMapping("/start-get-time")
    fun startPlaying(response: HttpServletResponse): SongResponse {

        if(StringUtils.isEmpty(songManager.actualSongName)) {
            println("There is no song set!")
            throw RuntimeException("No song set!")
        }

        if (!songManager.isPlaying) {
            println("startPlaying being called")
            songManager.startPlaying()
        }
        println("Time being requested")
        return SongResponse(songManager.currentTime(), songManager.actualSongName!!)
    }

    @RequestMapping(path= ["/set-song"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun setSong(@RequestBody songName: SongRequest) {
        songManager.setCurrentSong(songName.name)
    }
}