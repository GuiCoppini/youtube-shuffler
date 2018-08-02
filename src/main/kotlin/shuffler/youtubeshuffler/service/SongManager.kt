package shuffler.youtubeshuffler.service

import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import shuffler.youtubeshuffler.exception.NotFoundException
import java.io.File


@Service
class SongManager {
    var isPlaying = false
    var actualSongName: String? = null
    var actualSongMp3: File? = null

    var startTimeInNanoSeconds: Long = 0

    fun startPlaying() {
        if (startTimeInNanoSeconds == 0L)
            startTimeInNanoSeconds = System.nanoTime()
    }

    fun currentTime(): Double {
        return (System.nanoTime() - startTimeInNanoSeconds).div(1000000000.0)
    }

    fun getMp3ByName(name: String): File? {
        try {
            return ResourceUtils.getFile("classpath:${name}.mp3")
        } catch(e: Exception) {
            return null
        }
    }


    fun setCurrentSong(name: String) {
        val mp3 = getMp3ByName(name)
        if(mp3 == null) {
            throw NotFoundException("MP3 file named ${name} not found!")
        }
        actualSongMp3 = mp3!!
        actualSongName = name
        startPlaying()
    }
}