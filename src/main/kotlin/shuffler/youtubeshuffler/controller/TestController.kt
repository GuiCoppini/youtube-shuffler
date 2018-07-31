package shuffler.youtubeshuffler.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.ResourceUtils
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import shuffler.youtubeshuffler.service.SongTimer
import javax.servlet.http.HttpServletResponse

@CrossOrigin
@RestController
class TestController {


    @Autowired
    lateinit var songTimer: SongTimer

    @RequestMapping(path = ["/get-song"])
    fun getSong(response: HttpServletResponse) {
        println("Uploading MP3")
        val out = response.outputStream
        response.contentType = "audio/mpeg"
        val fileIn = ResourceUtils.getFile("classpath:tetris-mp3.mp3")
        val bytes = fileIn.readBytes()

        out.write(bytes)

        println("MP3 uploaded")
    }

    @RequestMapping("/start-get-time")
    fun startPlaying(response: HttpServletResponse) : Double {
        if(!songTimer.isPlaying) {
            println("startPlaying being called")
            songTimer.startPlaying()
        }
        println("Time being requested")
        return songTimer.currentTime()
    }
}