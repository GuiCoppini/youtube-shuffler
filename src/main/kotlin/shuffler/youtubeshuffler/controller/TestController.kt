package shuffler.youtubeshuffler.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE
import org.springframework.util.ResourceUtils
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import shuffler.youtubeshuffler.service.SongTimer
import java.util.*
import javax.servlet.http.HttpServletResponse

@CrossOrigin
@RestController
class TestController {


    @Autowired
    lateinit var songTimer: SongTimer

    @RequestMapping(path = ["/get-song"])
    fun getSong(response: HttpServletResponse) {
        val out = response.outputStream
        response.contentType = "audio/mpeg"
        val fileIn = ResourceUtils.getFile("classpath:tetris-mp3.mp3")
        val bytes = fileIn.readBytes()

        out.write(bytes)
    }

    @RequestMapping("/start-get-time")
    fun startPlaying(response: HttpServletResponse) : Long {
        val out = response.outputStream
        if(!songTimer.isPlaying) {
            songTimer.startPlaying()
        }
        return songTimer.currentTime
    }
}