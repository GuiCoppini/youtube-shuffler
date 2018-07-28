package shuffler.youtubeshuffler.service

import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader
import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import reactor.core.publisher.Flux


@Service
class SongTimer {

    val fileIn = ResourceUtils.getFile("classpath:tetris-mp3.mp3")
    var currentTime: Long = 0

    var isPlaying = false

//    val songDuration =

    fun startPlaying(): Flux<Long> {
        if (!isPlaying) {
            isPlaying = true
            val properties = MpegAudioFileReader().getAudioFileFormat(fileIn).properties()
            val duration: Long = properties["duration"] as Long / 1000000
            return Flux.create<Long> {
                while (currentTime < duration) {
                    it.next(currentTime + 1)
                    currentTime += 1
                    println("Tempo: $currentTime")
                    Thread.sleep(1000)
                }
                isPlaying = false
            }
        }
        return Flux.create {
            it.next(currentTime)
        }
    }
}