package shuffler.youtubeshuffler.service

import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils

@Service
class OutputBuffer(bufferSize: Int) {

    val buffer = ByteArray(bufferSize)
    var isPlaying: Boolean = false

    fun startPlaying() {
        if(isPlaying) return

        // preenche o buffer de N em N bytes a cada X ms
        val file = ResourceUtils.getFile("classpath:tetris-mp3.mp3")
        val inputStream = file.readBytes().inputStream()

        var bytesRead = inputStream.read(buffer)
        while (bytesRead > 0) {

            this.write(buffer)
            bytesRead = inputStream.read(buffer)
            Thread.sleep(50)
        }
    }


}