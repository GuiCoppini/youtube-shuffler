package shuffler.youtubeshuffler.service

import com.mpatric.mp3agic.Mp3File
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import reactor.core.publisher.Flux


@Service
class OutputBuffer {

    val mp3 = Mp3File(ResourceUtils.getFile("classpath:tetris-mp3.mp3"))
    // TODO deve virar um property depois
    val bufferSize: Int = mp3.bitrate * 1024 / 8

    var isPlaying: Boolean = false


    // bitrate = 64 kbps
    // 64 * 1024 / 8
    // 8192 bytes
    val inputStream = ResourceUtils.getFile("classpath:tetris-mp3.mp3")
            .readBytes()
            .inputStream()

    var buffer = ByteArray(bufferSize)

    @Async
    fun reactiveStartPlaying(): Flux<ByteArray> {

        var bytesRead = inputStream.read(buffer)

        val tempBuffer = ByteArray(bufferSize)

        return Flux.create<ByteArray> { sub ->
            if (bytesRead > 0)
                isPlaying = true // Evita fazer isso dentro do while toda hora

            while (bytesRead > 0) {
                fillBuffer(tempBuffer.copyOf())
                sub.next(buffer)
                bytesRead = inputStream.read(tempBuffer, 0, bytesRead)
                Thread.sleep(1000)
            }
            isPlaying = false
        }
    }

    fun fillBuffer(input: ByteArray): ByteArray {
        this.buffer = input.copyOf()
        return buffer
    }

    fun mp3Stats() {
        println("AS COISA DO MP3:")
        println(mp3.bitrate)
        println(mp3.sampleRate)
        println(mp3.lengthInSeconds)
    }
}





